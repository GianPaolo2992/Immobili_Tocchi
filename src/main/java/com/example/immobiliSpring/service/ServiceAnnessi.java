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

    private final AnnessiRepository annessiRepository;

    private final ImmobileRepository immobileRepository;
    private final ConverterAnnessi converterAnnessi;


    public ServiceAnnessi(AnnessiRepository annessiRepository, ImmobileRepository immobileRepository,ConverterAnnessi converterAnnessi) {

        this.annessiRepository = annessiRepository;
        this.immobileRepository = immobileRepository;
        this.converterAnnessi = converterAnnessi;
    }
    public List<Annessi> searchAnnessi(String keyword) {
        return annessiRepository.searchAnnessi(keyword);
    }

    public List<AnnessiDTO> getAllAnnessi() {
        List<AnnessiDTO> listaAnnessiDTO = new ArrayList<>();
        List<Annessi> listaAnnessi = this.annessiRepository.findAll();
        if (!listaAnnessi.isEmpty()) {
            for (Annessi annessi : listaAnnessi) {

                AnnessiDTO annessiDTO = this.converterAnnessi.ConvertToDTO(annessi);

                listaAnnessiDTO.add(annessiDTO);

            }
        }

        return listaAnnessiDTO;
    }

    public AnnessiDTO getAllAnnessiById(Integer id) {
        Optional<Annessi> annessiOpt = this.annessiRepository.findById(id);
        if (annessiOpt.isPresent()) {

            return this.converterAnnessi.ConvertToDTO(annessiOpt.get());

        } else {
            throw new EntityNotFoundException("Entity Not Found");
        }
    }

    public AnnessiDTO insertAnnessi(AnnessiDTO annessiDTO) {

        Annessi annessiSaved = ConverterAnnessi.ConvertToEntity(annessiDTO);
        this.annessiRepository.save(annessiSaved);
        return this.converterAnnessi.ConvertToDTO(annessiSaved);
    }

    public AnnessiDTO updateAnessi(Integer id, AnnessiDTO annessiDTO) {
        Annessi annessoOpt = this.annessiRepository.findById(id).orElseThrow(()-> new RuntimeException("Annessp non trovato"));


        annessoOpt = this.converterAnnessi.ConvertToEntityXUpdate(annessiDTO, annessoOpt);
            this.annessiRepository.save(annessoOpt);
            return  this.converterAnnessi.ConvertToDTO(annessoOpt);

    }

    public AnnessiDTO deleteAnessi(Integer id) {
        Optional<Annessi> annessiOpt = this.annessiRepository.findById(id);

        if (annessiOpt.isPresent()) {
            if(annessiOpt.get().getImmobile() != null){
                Optional<Immobile> immobileOpt = this.immobileRepository.findById(annessiOpt.get().getImmobile().getId());
                immobileOpt.ifPresent(immobile -> immobile.getListaAnnessi().remove(annessiOpt.get()));
            }


            AnnessiDTO annessiDeleted = this.converterAnnessi.ConvertToDTO(annessiOpt.get());
            this.annessiRepository.delete(annessiOpt.get());
            return annessiDeleted;
        } else {
            throw new EntityNotFoundException("Entity not Found");
        }

    }

    public List<Object[]> getCountBoxIntoDB() {
        return this.annessiRepository.countBoxIntoDB();
    }
}
