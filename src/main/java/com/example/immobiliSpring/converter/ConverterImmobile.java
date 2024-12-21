package com.example.immobiliSpring.converter;

import com.example.immobiliSpring.DTO.ImmobileDTO;
import com.example.immobiliSpring.entity.Immobile;

import java.util.ArrayList;
import java.util.List;

public class ConverterImmobile {

    public static List<ImmobileDTO> converterListToDTONoProp(List<Immobile> listaImmobili) {
        List<ImmobileDTO> listaimmobiliDTO = new ArrayList<>();
        if (!listaImmobili.isEmpty()) {
            for (Immobile immobile : listaImmobili){
                ImmobileDTO immobileDTO = new ImmobileDTO();

                immobileDTO.setId(immobile.getId());
                //no proprietario
                immobileDTO.setTipo(immobile.getTipo());
                immobileDTO.setVani(immobile.getVani());
                immobileDTO.setCosto(immobile.getCosto());
                immobileDTO.setSuperfice(immobile.getSuperfice());
                immobileDTO.setAnno(immobile.getAnno());
                if(immobile.getListaAnnessi() != null) {
                    immobileDTO.setListaAnnessiDTO(ConverterAnnessi.convertListToDTONoIMMBL(immobile.getListaAnnessi()));
                }
                listaimmobiliDTO.add(immobileDTO);

            }
        }
        return  listaimmobiliDTO;
    }

    public static List<Immobile> converterListToEntityNoProp(List<ImmobileDTO> listaImmobiliDTO) {
        List<Immobile> listaimmobili = new ArrayList<>();
        if (!listaImmobiliDTO.isEmpty()) {
            for (ImmobileDTO immobileDTO : listaImmobiliDTO){
                Immobile immobile = new Immobile();

                immobile.setId(immobileDTO.getId());
                //no proprietario
                immobile.setTipo(immobileDTO.getTipo());
                immobile.setVani(immobileDTO.getCosto());
                immobile.setSuperfice(immobileDTO.getSuperfice());
                immobile.setAnno(immobileDTO.getAnno());
                if(immobile.getListaAnnessi() != null) {
                    immobile.setListaAnnessi(ConverterAnnessi.convertListToEntityNoIMMBL(immobileDTO.getListaAnnessiDTO()));
                }
                listaimmobili.add(immobile);

            }
        }
        return  listaimmobili;
    }



    public static ImmobileDTO ConvertToDTO(Immobile immobile) {
        ImmobileDTO immobileDTO = new ImmobileDTO();

        immobileDTO.setId(immobile.getId());
        if ( immobile.getProprietari() != null) {
            immobileDTO.setProprietariDTO(ConverterProprietari.converterToDTOXImmobile(immobile.getProprietari()));
        }
        immobileDTO.setTipo(immobile.getTipo());
        immobileDTO.setVani(immobile.getVani());
        immobileDTO.setCosto(immobile.getCosto());
        immobileDTO.setSuperfice(immobile.getSuperfice());
        immobileDTO.setAnno(immobile.getAnno());
        if(immobile.getListaAnnessi() != null) {
            immobileDTO.setListaAnnessiDTO(ConverterAnnessi.convertListToDTONoIMMBL(immobile.getListaAnnessi()));
        }
        return immobileDTO;
    }

    public static Immobile ConvertToEntity(ImmobileDTO immobileDTO) {
        Immobile immobile = new Immobile();

        immobile.setId(immobileDTO.getId());
        if ( immobile.getProprietari() != null) {
            ConverterProprietari.converterToEntityXImmobili(immobileDTO.getProprietariDTO());
        }
        immobile.setTipo(immobileDTO.getTipo());
        immobile.setVani(immobileDTO.getVani());
        immobile.setCosto(immobileDTO.getCosto());
        immobile.setSuperfice(immobileDTO.getSuperfice());
        immobile.setAnno(immobileDTO.getAnno());
        if(immobileDTO.getListaAnnessiDTO() != null) {
            immobile.setListaAnnessi(ConverterAnnessi.convertListToEntityNoIMMBL(immobileDTO.getListaAnnessiDTO()));
        }
        return immobile;
    }
}
