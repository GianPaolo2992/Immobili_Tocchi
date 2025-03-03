package com.example.immobiliSpring.converter;

import com.example.immobiliSpring.DTO.AnnessiDTO;
import com.example.immobiliSpring.DTO.ImmobileDTO;
import com.example.immobiliSpring.entity.Annessi;
import com.example.immobiliSpring.entity.Immobile;
import com.example.immobiliSpring.entity.Proprietari;
import com.example.immobiliSpring.repository.AnnessiRepository;
import com.example.immobiliSpring.repository.ImmobileRepository;
import com.example.immobiliSpring.repository.ProrpietariRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ConverterImmobile {

    private final AnnessiRepository annessiRepository;
    private final ProrpietariRepository proprietariRepository;

    public ConverterImmobile(AnnessiRepository annessiRepository, ProrpietariRepository proprietariRepository) {
        this.annessiRepository = annessiRepository;
        this.proprietariRepository = proprietariRepository;
    }

    public static List<ImmobileDTO> converterListToDTONoProp(List<Immobile> listaImmobili) {
        List<ImmobileDTO> listaimmobiliDTO = new ArrayList<>();
        if (!listaImmobili.isEmpty()) {
            for (Immobile immobile : listaImmobili) {
                ImmobileDTO immobileDTO = new ImmobileDTO();

                immobileDTO.setId(immobile.getId());
                //no proprietario
                immobileDTO.setTipo(immobile.getTipo());
                immobileDTO.setVani(immobile.getVani());
                immobileDTO.setCosto(immobile.getCosto());
                immobileDTO.setSuperfice(immobile.getSuperficie());
                immobileDTO.setAnno(immobile.getAnno());
                if (immobile.getListaAnnessi() != null) {
                    immobileDTO.setListaAnnessiDTO(ConverterAnnessi.convertListToDTONoIMMBL(immobile.getListaAnnessi()));
                }
                listaimmobiliDTO.add(immobileDTO);

            }
        }
        return listaimmobiliDTO;
    }

    public static List<Immobile> converterListToEntityNoProp(List<ImmobileDTO> listaImmobiliDTO) {
        List<Immobile> listaimmobili = new ArrayList<>();
        if (!listaImmobiliDTO.isEmpty()) {
            for (ImmobileDTO immobileDTO : listaImmobiliDTO) {
                Immobile immobile = new Immobile();

                immobile.setId(immobileDTO.getId());
                //no proprietario
                immobile.setTipo(immobileDTO.getTipo());
                immobile.setVani(immobileDTO.getCosto());
                immobile.setSuperficie(immobileDTO.getSuperfice());
                immobile.setAnno(immobileDTO.getAnno());
                if (immobile.getListaAnnessi() != null) {
                    immobile.setListaAnnessi(ConverterAnnessi.convertListToEntityNoIMMBL(immobileDTO.getListaAnnessiDTO()));
                }
                listaimmobili.add(immobile);

            }
        }
        return listaimmobili;
    }


    public static ImmobileDTO ConvertToDTOXAnnessi(Immobile immobile) {
        ImmobileDTO immobileDTO = new ImmobileDTO();

        immobileDTO.setId(immobile.getId());
        if (immobile.getProprietari() != null) {
            immobileDTO.setProprietariDTO(ConverterProprietari.converterToDTOXImmobile(immobile.getProprietari()));
        }
        immobileDTO.setTipo(immobile.getTipo());
        immobileDTO.setVani(immobile.getVani());
        immobileDTO.setCosto(immobile.getCosto());
        immobileDTO.setSuperfice(immobile.getSuperficie());
        immobileDTO.setAnno(immobile.getAnno());

        return immobileDTO;
    }

    public static Immobile ConvertToEntityXAnnessi(ImmobileDTO immobileDTO) {
        Immobile immobile = new Immobile();

        immobile.setId(immobileDTO.getId());
        if (immobile.getProprietari() != null) {
            immobile.setProprietari(ConverterProprietari.converterToEntityXAnnessi(immobileDTO.getProprietariDTO()));
        }
        immobile.setTipo(immobileDTO.getTipo());
        immobile.setVani(immobileDTO.getVani());
        immobile.setCosto(immobileDTO.getSuperfice());
        immobile.setAnno(immobileDTO.getAnno());

        return immobile;
    }

    public static ImmobileDTO ConvertToDTO(Immobile immobile) {
        ImmobileDTO immobileDTO = new ImmobileDTO();

        immobileDTO.setId(immobile.getId());
        if (immobile.getProprietari() != null) {
            immobileDTO.setProprietariDTO(ConverterProprietari.converterToDTOXImmobile(immobile.getProprietari()));
        }
        immobileDTO.setTipo(immobile.getTipo());
        immobileDTO.setVani(immobile.getVani());
        immobileDTO.setCosto(immobile.getCosto());
        immobileDTO.setSuperfice(immobile.getSuperficie());
        immobileDTO.setAnno(immobile.getAnno());
        if (immobile.getListaAnnessi() != null) {
            immobileDTO.setListaAnnessiDTO(ConverterAnnessi.convertListToDTONoIMMBL(immobile.getListaAnnessi()));
        }
        return immobileDTO;
    }

    public Immobile ConvertToEntity(ImmobileDTO immobileDTO) {
        Immobile immobile = new Immobile();

        immobile.setId(immobileDTO.getId());
        if (immobileDTO.getProprietariDTO() != null) {
            Proprietari proprietario = this.proprietariRepository.findById(immobileDTO.getProprietariDTO().getId()).orElseThrow();
            immobile.setProprietari(proprietario);
        }
        immobile.setTipo(immobileDTO.getTipo());
        immobile.setVani(immobileDTO.getVani());
        immobile.setCosto(immobileDTO.getCosto());
        immobile.setSuperficie(immobileDTO.getSuperfice());
        immobile.setAnno(immobileDTO.getAnno());

        if (immobileDTO.getListaAnnessiDTO() != null) {

            List<Annessi> annessiList = new ArrayList<>();

            for (AnnessiDTO annessoDTO : immobileDTO.getListaAnnessiDTO()) {

                Annessi annesso = this.annessiRepository.findById(annessoDTO.getId()).orElseThrow();
                annesso.setImmobile(immobile);
                annessiList.add(annesso);
            }

            immobile.setListaAnnessi(annessiList);
        }
        return immobile;
    }

//    public Immobile ConvertToEntityXUpdate(ImmobileDTO immobileDTO, Immobile immobile) {
//
//
//        if (immobileDTO.getProprietariDTO() != null) {
//            Proprietari proprietario = this.proprietariRepository.findById(immobileDTO.getProprietariDTO().getId()).orElseThrow();
//            immobile.setProprietari(proprietario);
//        }
//        immobile.setTipo(immobileDTO.getTipo());
//        immobile.setVani(immobileDTO.getVani());
//        immobile.setCosto(immobileDTO.getCosto());
//        immobile.setSuperficie(immobileDTO.getSuperfice());
//        immobile.setAnno(immobileDTO.getAnno());
//
//        if (immobileDTO.getListaAnnessiDTO().isEmpty()) {
//
//            List<Annessi> annessiList = new ArrayList<>();
//
//            for (AnnessiDTO annessoDTO : immobileDTO.getListaAnnessiDTO()) {
//                for(Annessi annesso: immobile.getListaAnnessi()){
//                    if(Objects.equals(annesso.getId(), annessoDTO.getId())){
//                        annesso.setImmobile(immobile);
//                        annessiList.add(annesso);
//                    } else {
//                        annesso.setImmobile(null);
//                    }
//                }
//              Annessi annesso = this.annessiRepository.findById(annessoDTO.getId()).orElseThrow();
//            }
//
//            immobile.setListaAnnessi(annessiList);
//        }
//        return immobile;
//    }
public Immobile ConvertToEntityXUpdate(ImmobileDTO immobileDTO, Immobile immobile) {
    if (immobileDTO.getProprietariDTO() != null) {
        Proprietari proprietario = this.proprietariRepository.findById(immobileDTO.getProprietariDTO().getId()).orElseThrow();
        immobile.setProprietari(proprietario);
    } else{
        immobile.setProprietari(null);
    }

    immobile.setTipo(immobileDTO.getTipo());
    immobile.setVani(immobileDTO.getVani());
    immobile.setCosto(immobileDTO.getCosto());
    immobile.setSuperficie(immobileDTO.getSuperfice());
    immobile.setAnno(immobileDTO.getAnno());

    if (immobileDTO.getListaAnnessiDTO() != null) {
        List<Annessi> annessiList = new ArrayList<>();

        // Aggiungere nuovi annessi o aggiornare quelli esistenti
        for (AnnessiDTO annessoDTO : immobileDTO.getListaAnnessiDTO()) {
            Annessi annesso = this.annessiRepository.findById(annessoDTO.getId()).orElse(null);

            if (annesso != null) {
                annesso.setImmobile(immobile);
                annessiList.add(annesso);
            }
        }

        // Rimuovere annessi non più associati
        for (Annessi annesso : immobile.getListaAnnessi()) {
            boolean stillPresent = immobileDTO.getListaAnnessiDTO().stream()
                    .anyMatch(annessoDTO -> Objects.equals(annessoDTO.getId(), annesso.getId()));

            if (!stillPresent) {
                annesso.setImmobile(null);
            }
        }

        immobile.setListaAnnessi(annessiList);
    }

    return immobile;
}

}
