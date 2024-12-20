package com.example.immobiliSpring.service;

import com.example.immobiliSpring.DTO.ProprietariDTO;
import com.example.immobiliSpring.converter.ConverterProprietari;
import com.example.immobiliSpring.entity.Proprietari;
import com.example.immobiliSpring.repository.ProrpietariRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceProprietari{

    private final ProrpietariRepository prorpietariRepository;

    public ServiceProprietari(ProrpietariRepository prorpietariRepository){
        this.prorpietariRepository = prorpietariRepository;
    }

    public ProprietariDTO getProprietariById(Integer id) {
        Optional<Proprietari> proprietariOPT = prorpietariRepository.findById(id);

        if (proprietariOPT.isPresent()) {

            ProprietariDTO proprietariDTO = ConverterProprietari.converterToDTO(proprietariOPT.get());
            return proprietariDTO;
        } else {
            throw new EntityNotFoundException("Proprietario Not Found");
        }
    }
}
