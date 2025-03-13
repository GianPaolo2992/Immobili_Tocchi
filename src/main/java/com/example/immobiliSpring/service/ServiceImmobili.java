package com.example.immobiliSpring.service;

import com.example.immobiliSpring.DTO.ImmobileDTO;
import com.example.immobiliSpring.converter.ConverterImmobile;
import com.example.immobiliSpring.entity.Annessi;
import com.example.immobiliSpring.entity.Immobile;
import com.example.immobiliSpring.repository.AnnessiRepository;
import com.example.immobiliSpring.repository.ImmobileRepository;
import com.example.immobiliSpring.repository.ProrpietariRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceImmobili {
    private boolean isDelete = false;
    private final ImmobileRepository immobileRepository;
    private final AnnessiRepository annessiRepository;
    private final ProrpietariRepository proprietariRepository;
    private final ConverterImmobile converterImmobile;

    public ServiceImmobili(ProrpietariRepository proprietariRepository, ImmobileRepository immobileRepository, AnnessiRepository annessiRepository, ConverterImmobile converterImmobile) {
        this.immobileRepository = immobileRepository;
        this.annessiRepository = annessiRepository;
        this.converterImmobile = converterImmobile;
        this.proprietariRepository = proprietariRepository;
    }
    public List<ImmobileDTO> searchImmobile(String keyword) {
        return immobileRepository.searchImmobile(keyword).stream().map(ConverterImmobile::ConvertToDTO).toList();
    }

    public List<ImmobileDTO> getAllImmobili() {
        List<ImmobileDTO> immobileDTOList = new ArrayList<>();
        List<Immobile> immobileList = immobileRepository.findAll();
        if (!immobileList.isEmpty()) {
            for (Immobile immobile : immobileList) {
                ImmobileDTO immobileDTO = new ImmobileDTO();
                immobileDTO = ConverterImmobile.ConvertToDTO(immobile);
                immobileDTOList.add(immobileDTO);
            }
            return immobileDTOList;
        } else {
            throw new EntityNotFoundException("Lista Vuota");
        }
    }

    public ImmobileDTO getImmobileById(Integer id) {
        Optional<Immobile> immobileOpt = immobileRepository.findById(id);
        if (immobileOpt.isPresent()) {

            return ConverterImmobile.ConvertToDTO(immobileOpt.get());
        } else {
            throw new EntityNotFoundException("Entity Not Found");
        }
    }

    public ImmobileDTO insertImmobile(ImmobileDTO immobileDTO) {
        Immobile immobileSaved = this.converterImmobile.ConvertToEntity(immobileDTO);
        immobileRepository.save(immobileSaved);
        return ConverterImmobile.ConvertToDTO(immobileSaved);
    }


    public ImmobileDTO updateImmobile(Integer id, ImmobileDTO immobileDTO) {
        Immobile immobileOpt = immobileRepository.findById(id).orElseThrow(() -> new RuntimeException("immobilie non trovato"));
        immobileOpt = this.converterImmobile.ConvertToEntityXUpdate(immobileDTO, immobileOpt);

        try {
            immobileRepository.save(immobileOpt);
            return ConverterImmobile.ConvertToDTO(immobileOpt);
        } catch (Exception e) {

            System.err.println("Errore durante l'inserimento del proprietario: " + e.getMessage());

            throw new RuntimeException("Errore durante l'inserimento del proprietario", e);
        }


    }


    public ImmobileDTO deleteImmobileById(Integer id) {
        Immobile immobileOPT = immobileRepository.findById(id).orElseThrow(() -> new RuntimeException("immobile non trovato"));
        List<Annessi> annessiListCopy = new ArrayList<>();
        if (!immobileOPT.getListaAnnessi().isEmpty()) {
            annessiListCopy = immobileOPT.getListaAnnessi();
            for (Annessi annesso : annessiListCopy) {
                Annessi annessoEntity = annessiRepository.findById(annesso.getId()).orElseThrow();
                annessoEntity.setImmobile(null);
                annessiRepository.save(annessoEntity);
            }

        }
        immobileRepository.delete(immobileOPT);
        return ConverterImmobile.ConvertToDTO(immobileOPT);
    }


    public ImmobileDTO AssociateAnnessi(Integer idAnnesso, Integer idImmbl) {
        Optional<Immobile> immobileOptional = immobileRepository.findById(idImmbl);
        Optional<Annessi> annessiOptional = annessiRepository.findById(idAnnesso);

        if (annessiOptional.isPresent() && immobileOptional.isPresent() && annessiOptional.get().getImmobile() == null) {
            immobileOptional.get().getListaAnnessi().add(annessiOptional.get());
            annessiOptional.get().setImmobile(immobileOptional.get());

            annessiRepository.save(annessiOptional.get());
            immobileRepository.save(immobileOptional.get());

            return ConverterImmobile.ConvertToDTO(immobileOptional.get());
        } else {
            throw new EntityNotFoundException("Entity Not Found");
        }

    }

    public ImmobileDTO DissociaAnnessi(Integer id) {
        Annessi annesso = annessiRepository.findById(id).orElseThrow(() -> new RuntimeException("annesso non trovato"));
        Immobile immobile = immobileRepository.findById(annesso.getImmobile().getId()).orElseThrow(() -> new RuntimeException("immobile on trovato non trovato"));
        try {
            if (immobile.getListaAnnessi().contains(annesso) && annesso.getImmobile() == immobile) {

//                if (!this.isDelete) {
                immobile.getListaAnnessi().remove(annesso);
                immobileRepository.save(immobile);
//                }
                annesso.setImmobile(null);
                annessiRepository.save(annesso);

            } else {
                throw new EntityNotFoundException("Entity Not Found");
            }

        } catch (Exception e) {
            System.err.println("Errore durante la dissociazione dell'annesso: " + e.getMessage());
            throw new RuntimeException("Errore durante la dissociazione dell'annesso", e);
        }
        this.isDelete = false;
        return ConverterImmobile.ConvertToDTO(immobile);
    }

    public List<Object[]> getVillaWithGarden() {
        return immobileRepository.VilleWithGarden();
    }


    public List<ImmobileDTO> findImmobiliAfter1996() {
        List<ImmobileDTO> listImmobiliBefore1996 = new ArrayList<>();
        List<Immobile> immobileList = immobileRepository.findImmobiliAfter1996();
        if (!immobileList.isEmpty()) {
            for (Immobile immobile : immobileList) {
                ImmobileDTO immobileDTO = ConverterImmobile.ConvertToDTO(immobile);
                listImmobiliBefore1996.add(immobileDTO);

            }
        }

        return listImmobiliBefore1996;
    }

    public List<ImmobileDTO> findImmobiliNullProp() {
        List<ImmobileDTO> listImmobiliNullProp = new ArrayList<>();
        List<Immobile> immobileList = immobileRepository.findImmobiliNullProp();
        if (!immobileList.isEmpty()) {
            for (Immobile immobile : immobileList) {
                ImmobileDTO immobileDTO = ConverterImmobile.ConvertToDTO(immobile);
                listImmobiliNullProp.add(immobileDTO);

            }

        }
        return listImmobiliNullProp;
    }
}
