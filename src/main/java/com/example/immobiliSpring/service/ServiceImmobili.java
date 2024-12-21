package com.example.immobiliSpring.service;

import com.example.immobiliSpring.DTO.ImmobileDTO;
import com.example.immobiliSpring.converter.ConverterImmobile;
import com.example.immobiliSpring.entity.Annessi;
import com.example.immobiliSpring.entity.Immobile;
import com.example.immobiliSpring.repository.AnnessiRepository;
import com.example.immobiliSpring.repository.ImmobileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceImmobili {

    private final ImmobileRepository immobileRepository;
    private final AnnessiRepository annessiRepository;

    public  ServiceImmobili(ImmobileRepository immobileRepository,AnnessiRepository annessiRepository){
        this.immobileRepository = immobileRepository;
        this.annessiRepository = annessiRepository;
    }
    public List<ImmobileDTO> getAllImmobili(){
        List<ImmobileDTO> immobileDTOList = new ArrayList<>();
        List<Immobile> immobileList = immobileRepository.findAll();
        if(!immobileList.isEmpty()) {
            for(Immobile immobile : immobileList) {
                ImmobileDTO immobileDTO = new ImmobileDTO();
                immobileDTO = ConverterImmobile.ConvertToDTO(immobile);
                immobileDTOList.add(immobileDTO);
            }
            return immobileDTOList;
        } else {
            throw new EntityNotFoundException("Lista Vuota");
        }
    }

    public ImmobileDTO getImmobileById(Integer id){
       Optional<Immobile> immobileOpt = immobileRepository.findById(id);
        if(immobileOpt.isPresent()) {

            return ConverterImmobile.ConvertToDTO(immobileOpt.get());
        } else {
            throw new EntityNotFoundException("Entity Not Found");
        }
    }

    public ImmobileDTO insertImmobile(ImmobileDTO immobileDTO){
        Immobile immobileSaved =ConverterImmobile.ConvertToEntity(immobileDTO);
        immobileRepository.save(immobileSaved);
        return ConverterImmobile.ConvertToDTO(immobileSaved);
    };

    public ImmobileDTO updateImmobile(Integer id,ImmobileDTO immobileDTO) {
        Optional<Immobile> immobileOpt = immobileRepository.findById(id);

        if(immobileOpt.isPresent()) {

            immobileDTO.setId(id);
            Immobile immobileUpdated = ConverterImmobile.ConvertToEntity(immobileDTO);
            immobileRepository.save(immobileUpdated);
            return ConverterImmobile.ConvertToDTO(immobileUpdated);
        } else {
            throw new EntityNotFoundException("Entity Not Found");
        }
    }

    public ImmobileDTO deleteImmobileById(Integer id) {
        Optional<Immobile> immobileOPT = immobileRepository.findById(id);

        if (immobileOPT.isPresent()) {
            ImmobileDTO immobileDeleted = ConverterImmobile.ConvertToDTO(immobileOPT.get());
            if (immobileOPT.get().getProprietari() == null) {
                if (immobileOPT.get().getListaAnnessi() !=null) {

                    for (Annessi annessi : immobileOPT.get().getListaAnnessi()){
                        annessi.setImmobile(null);
                    }
                }

                immobileRepository.delete(immobileOPT.get());
            }


            return immobileDeleted;
        }else {
            throw new EntityNotFoundException("Entity Not Found");
        }
    }

    public  ImmobileDTO AssociateAnnessi(Integer idAnnesso,Integer idImmbl) {
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
        Optional<Annessi> annessiOptional = annessiRepository.findById(id);
        Optional<Immobile> immobileOptional = immobileRepository.findById(annessiOptional.get().getImmobile().getId());
        if (immobileOptional.isPresent()){
            if ( immobileOptional.get().getListaAnnessi().contains(annessiOptional.get()) && annessiOptional.get().getImmobile() == immobileOptional.get()) {

                immobileOptional.get().getListaAnnessi().remove(annessiOptional.get());
                annessiOptional.get().setImmobile(null);

                annessiRepository.save(annessiOptional.get());
                immobileRepository.save(immobileOptional.get());

            } else {
                throw new EntityNotFoundException("Entity Not Found");
            }

        }
        return ConverterImmobile.ConvertToDTO(immobileOptional.get());

    }
}
