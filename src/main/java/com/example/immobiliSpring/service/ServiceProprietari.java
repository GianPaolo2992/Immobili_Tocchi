package com.example.immobiliSpring.service;

import com.example.immobiliSpring.DTO.ProprietariDTO;
import com.example.immobiliSpring.converter.ConverterProprietari;
import com.example.immobiliSpring.entity.Immobile;
import com.example.immobiliSpring.entity.Proprietari;
import com.example.immobiliSpring.repository.ImmobileRepository;
import com.example.immobiliSpring.repository.ProrpietariRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceProprietari {

    private final ProrpietariRepository prorpietariRepository;
    private final ImmobileRepository immobileRepository;

    public ServiceProprietari(ProrpietariRepository prorpietariRepository, ImmobileRepository immobileRepository) {
        this.prorpietariRepository = prorpietariRepository;
        this.immobileRepository = immobileRepository;
    }

    @Transactional
    public ProprietariDTO getProprietariById(Integer id) {
        Optional<Proprietari> proprietariOPT = prorpietariRepository.findById(id);

        if (proprietariOPT.isPresent()) {

            ProprietariDTO proprietariDTO = ConverterProprietari.converterToDTO(proprietariOPT.get());
            return proprietariDTO;
        } else {
            throw new EntityNotFoundException("Proprietario Not Found");
        }
    }

    @Transactional
    public List<ProprietariDTO> getAllProprietari() {
        List<ProprietariDTO> listaPropDTO = new ArrayList<>();
        List<Proprietari> listaProprietari = prorpietariRepository.findAll();

        for (Proprietari prop : listaProprietari) {
            ProprietariDTO propDTO = ConverterProprietari.converterToDTO(prop);
            listaPropDTO.add(propDTO);
        }

        return listaPropDTO;
    }

    @Transactional
    public ProprietariDTO insertProprietari(ProprietariDTO proprietariDTO) {

        Proprietari propSaved = ConverterProprietari.converterToEntityXInsert(proprietariDTO);
        prorpietariRepository.save(propSaved);
        return ConverterProprietari.converterToDTO(propSaved);
    }

    @Transactional

    public ProprietariDTO updateProprietario(Integer id, ProprietariDTO proprietariDTO) {
        Optional<Proprietari> propOPT = prorpietariRepository.findById(id);

        if (propOPT.isPresent()) {
            proprietariDTO.setId(id);
//           propOPT.get().setNome(proprietariDTO.getNome());
//           propOPT.get().setCognome(proprietariDTO.getCognome());

//           Proprietari propUpdated = propOPT.get();
            Proprietari propUpdated = ConverterProprietari.converterToEntityXInsert(proprietariDTO);
            prorpietariRepository.save(propOPT.get());
            return ConverterProprietari.converterToDTO(propUpdated);
        } else {
            throw new EntityNotFoundException("Proprietario Not Found");
        }
    }

    @Transactional
    public ProprietariDTO deleteProprietario(Integer id) {
        Optional<Proprietari> propOPT = prorpietariRepository.findById(id);
        if (propOPT.isPresent()) {

            Proprietari propDeleted = propOPT.get();

            if (!propDeleted.getListaImmobili().isEmpty()) {
                for (Immobile immobile : propDeleted.getListaImmobili()) {
                    immobile.setProprietari(null);
                }
            }

            prorpietariRepository.delete(propOPT.get());
            return ConverterProprietari.converterToDTO(propDeleted);
        } else {
            throw new EntityNotFoundException("Prop Not Found");
        }
    }

    @Transactional
    public ProprietariDTO AssocateImmobile(Integer idProp, Integer idImmbl) {
        Optional<Proprietari> propOPT = prorpietariRepository.findById(idProp);
        Optional<Immobile> immobileOPT = immobileRepository.findById(idImmbl);
        if (propOPT.isPresent() && immobileOPT.isPresent()  ) {
            Proprietari propAssociate = propOPT.get();
            if(immobileOPT.get().getProprietari() == null ) {

                Immobile immblAssociate = immobileOPT.get();

                propAssociate.getListaImmobili().add(immblAssociate);
                immblAssociate.setProprietari(propAssociate);

                prorpietariRepository.save(propOPT.get());
                immobileRepository.save(immblAssociate);

            }else{
                System.out.println("non è possibile associare questo immobile perche appartenente ad un altro prop");
            }
            return ConverterProprietari.converterToDTO(propAssociate);
        } else {
            throw new EntityNotFoundException("Prop Not Found");
        }
    }

    public List<Object[]> getSumSuperficeProp() {
        return prorpietariRepository.sumSuperficeProp();
    }

    public List<Object> sumSuperficePropName( String nome,String cognome) {
        return prorpietariRepository.sumSuperficePropName(nome,cognome);
    }

    public List<Object[]> getPropOfVilla() {
        return prorpietariRepository.propOfVilla();
    }

   public List<Object[]> getPropAppartmentWithBox() {
        return prorpietariRepository.propAppartmentWithBox();
    }

    public List<Object[]> getPropTotVani() {
//        List<ProprietariDTO> listPropVaniDTO = new ArrayList<>();
//        List<Proprietari> listPropVani = prorpietariRepository.propTotVani();
//        if (!listPropVani.isEmpty()) {
//            for (Proprietari proprietari : listPropVani) {
//                ProprietariDTO proprietariDTO = ConverterProprietari.converterToDTO(proprietari);
//                    listPropVaniDTO.add(proprietariDTO);
//            }
//        }
//        return listPropVaniDTO;
        return prorpietariRepository.propTotVani();
    }

    public List<Object[]> getOwnersWithMore400MQ() {
        return prorpietariRepository.ownersWithMore400MQ();
    }

}
