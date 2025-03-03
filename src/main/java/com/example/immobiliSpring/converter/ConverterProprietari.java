package com.example.immobiliSpring.converter;

import com.example.immobiliSpring.DTO.ImmobileDTO;
import com.example.immobiliSpring.DTO.ProprietariDTO;
import com.example.immobiliSpring.entity.Immobile;
import com.example.immobiliSpring.entity.Proprietari;
import com.example.immobiliSpring.repository.ImmobileRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ConverterProprietari {
    private final ImmobileRepository immobileRepository;

    public ConverterProprietari(ImmobileRepository immobileRepository) {
        this.immobileRepository = immobileRepository;
    }

    public ProprietariDTO converterToDTO(Proprietari proprietari) {
        ProprietariDTO proprietariDTO = new ProprietariDTO();

        proprietariDTO.setId(proprietari.getId());
        proprietariDTO.setNome(proprietari.getNome());
        proprietariDTO.setCognome(proprietari.getCognome());
        if (proprietari.getListaImmobili() != null) {
            proprietariDTO.setListaImmobiliDTO(ConverterImmobile.converterListToDTONoProp(proprietari.getListaImmobili()));

        }
        return proprietariDTO;
    }

    public static ProprietariDTO converterToDTOXImmobile(Proprietari proprietari) {
        ProprietariDTO proprietariDTO = new ProprietariDTO();

        proprietariDTO.setId(proprietari.getId());
        proprietariDTO.setNome(proprietari.getNome());
        proprietariDTO.setCognome(proprietari.getCognome());

        return proprietariDTO;
    }

    public static Proprietari converterToEntityXAnnessi(ProprietariDTO proprietariDTO) {
        Proprietari proprietari = new Proprietari();

        proprietari.setId(proprietariDTO.getId());
        proprietari.setNome(proprietariDTO.getNome());
        proprietari.setCognome(proprietariDTO.getCognome());

        return proprietari;
    }

    public static Proprietari converterToEntity(ProprietariDTO proprietariDTO) {
        Proprietari proprietari = new Proprietari();

        proprietari.setId(proprietariDTO.getId());
        proprietari.setNome(proprietariDTO.getNome());
        proprietari.setCognome(proprietariDTO.getCognome());
        if (proprietariDTO.getListaImmobiliDTO() != null) {
            proprietari.setListaImmobili(ConverterImmobile.converterListToEntityNoProp(proprietariDTO.getListaImmobiliDTO()));

        }
        return proprietari;
    }

    public static Proprietari converterToEntityXImmobili(ProprietariDTO proprietariDTO) {
        Proprietari proprietari = new Proprietari();

        proprietari.setId(proprietariDTO.getId());
        proprietari.setNome(proprietariDTO.getNome());
        proprietari.setCognome(proprietariDTO.getCognome());

        return proprietari;
    }

    public Proprietari converterToEntityXInsert(ProprietariDTO proprietariDTO) {
        Proprietari proprietari = new Proprietari();

        proprietari.setId(proprietariDTO.getId());
        proprietari.setNome(proprietariDTO.getNome());
        proprietari.setCognome(proprietariDTO.getCognome());
        if (proprietariDTO.getListaImmobiliDTO() != null) {
            List<Immobile> immobileList = new ArrayList<>();

            for (ImmobileDTO immobileDTO : proprietariDTO.getListaImmobiliDTO()) {
                Immobile immobile = this.immobileRepository.findById(immobileDTO.getId()).orElseThrow();
                immobile.setProprietari(proprietari);
                immobileList.add(immobile);
            }
            proprietari.setListaImmobili(immobileList);
        }
        return proprietari;
    }

    //    public Proprietari converterToEntityXUpdate(ProprietariDTO proprietariDTO, Proprietari proprietari) {
//
//
//        proprietari.setNome(proprietariDTO.getNome());
//        proprietari.setCognome(proprietariDTO.getCognome());
//        if (proprietariDTO.getListaImmobiliDTO() != null) {
//            List<Immobile> immobileList = new ArrayList<>();
//
//            for (ImmobileDTO immobileDTO : proprietariDTO.getListaImmobiliDTO()) {
//                for (Immobile immobile : proprietari.getListaImmobili()) {
//                    if (Objects.equals(immobile.getId(), immobileDTO.getId())) {
//                        immobile = this.immobileRepository.findById(immobileDTO.getId()).orElseThrow();
//                        immobile.setProprietari(proprietari);
//                        immobileList.add(immobile);
//                    } else {
//                        immobile.setProprietari(null);
//                    }
//                }
//            }
//
//            proprietari.setListaImmobili(immobileList);
//
//        }
//        return proprietari;
//    }
    public Proprietari converterToEntityXUpdate(ProprietariDTO proprietariDTO, Proprietari proprietari) {
        proprietari.setNome(proprietariDTO.getNome());
        proprietari.setCognome(proprietariDTO.getCognome());

        if (proprietariDTO.getListaImmobiliDTO() != null) {
            List<Immobile> immobileList = new ArrayList<>();

            // Prima iteriamo sugli immobili DTO
            for (ImmobileDTO immobileDTO : proprietariDTO.getListaImmobiliDTO()) {
                Immobile immobile = this.immobileRepository.findById(immobileDTO.getId()).orElse(null);

                if (immobile != null) {
                    immobile.setProprietari(proprietari);
                    immobileList.add(immobile);
                }
            }

            // Ora gestiamo gli immobili esistenti che potrebbero non essere più associati
            for (Immobile immobile : proprietari.getListaImmobili()) {
                boolean stillPresent = proprietariDTO.getListaImmobiliDTO().stream()
                        .anyMatch(immobileDTO -> Objects.equals(immobileDTO.getId(), immobile.getId()));

                if (!stillPresent) {
                    immobile.setProprietari(null);
                }
            }

            proprietari.setListaImmobili(immobileList);
        }

        return proprietari;
    }

}
