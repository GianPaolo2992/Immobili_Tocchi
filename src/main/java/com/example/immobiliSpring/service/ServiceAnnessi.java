package com.example.immobiliSpring.service;

import com.example.immobiliSpring.DTO.AnnessiDTO;
import com.example.immobiliSpring.controller.ControllerAnnessi;
import com.example.immobiliSpring.converter.ConverterAnnessi;
import com.example.immobiliSpring.entity.Annessi;
import com.example.immobiliSpring.entity.Immobile;
import com.example.immobiliSpring.repository.AnnessiRepository;
import com.example.immobiliSpring.repository.ImmobileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceAnnessi {
    @Autowired
    private  AnnessiRepository annessiRepository;
    @Autowired

    private  ImmobileRepository immobileRepository;

//    public ServiceAnnessi(AnnessiRepository annessiRepository, ImmobileRepository immobileRepository) {
//
//        this.annessiRepository = annessiRepository;
//        this.immobileRepository = immobileRepository;
//    }

    public List<AnnessiDTO> getAllAnnessi() {
        List<AnnessiDTO> listaAnnessiDTO = new ArrayList<>();
        List<Annessi> listaAnnessi = annessiRepository.findAll();
        if (!listaAnnessi.isEmpty()) {
            for (Annessi annessi : listaAnnessi) {

                AnnessiDTO annessiDTO = ConverterAnnessi.ConvertToDTO(annessi);

                listaAnnessiDTO.add(annessiDTO);

            }
        }

        return listaAnnessiDTO;
    }

    public AnnessiDTO getAllAnnessiById(Integer id) {
        Optional<Annessi> annessiOpt = annessiRepository.findById(id);
        if (annessiOpt.isPresent()) {
            return ConverterAnnessi.ConvertToDTO(annessiOpt.get());

        } else {
            throw new EntityNotFoundException("Entity Not Found");
        }
    }

    public AnnessiDTO insertAnnessi(AnnessiDTO annessiDTO) {

        Annessi annessiSaved = ConverterAnnessi.ConvertToEntity(annessiDTO);
        annessiRepository.save(annessiSaved);
        return ConverterAnnessi.ConvertToDTO(annessiSaved);
    }

    public AnnessiDTO updateAnessi(Integer id, AnnessiDTO annessiDTO) {
        Optional<Annessi> annessoOpt = annessiRepository.findById(id);

        if (annessoOpt.isPresent()) {
            annessiDTO.setId(id);
          Annessi annessiUpdated = ConverterAnnessi.ConvertToEntity(annessiDTO);
            annessiRepository.save(annessiUpdated);
            return  ConverterAnnessi.ConvertToDTO(annessiUpdated);
        } else {
            throw new EntityNotFoundException("Entity Not Found");
        }
    }

    public AnnessiDTO deleteAnessi(Integer id) {
        Optional<Annessi> annessiOpt = annessiRepository.findById(id);

        if (annessiOpt.isPresent()) {
            Optional<Immobile> immobileOpt = immobileRepository.findById(annessiOpt.get().getImmobile().getId());
            immobileOpt.ifPresent(immobile -> immobile.getListaAnnessi().remove(annessiOpt.get()));

            AnnessiDTO annessiDeleted = ConverterAnnessi.ConvertToDTO(annessiOpt.get());
            annessiRepository.delete(annessiOpt.get());
            return annessiDeleted;
        } else {
            throw new EntityNotFoundException("Entity not Found");
        }

    }

    public List<Object[]> getCountBoxIntoDB() {
        return annessiRepository.countBoxIntoDB();
    }
}
