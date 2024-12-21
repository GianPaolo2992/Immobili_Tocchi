package com.example.immobiliSpring.converter;

import com.example.immobiliSpring.DTO.ProprietariDTO;
import com.example.immobiliSpring.entity.Proprietari;

import java.util.ArrayList;

public class ConverterProprietari {

    public static ProprietariDTO converterToDTO(Proprietari proprietari) {
        ProprietariDTO proprietariDTO = new ProprietariDTO();

        proprietariDTO.setId(proprietari.getId());
        proprietariDTO.setNome(proprietari.getNome());
        proprietariDTO.setCognome(proprietari.getCognome());
        if(proprietari.getListaImmobili() != null) {
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

    public static Proprietari converterToEntity(ProprietariDTO proprietariDTO) {
        Proprietari proprietari = new Proprietari();

        proprietariDTO.setId(proprietariDTO.getId());
        proprietari.setNome(proprietariDTO.getNome());
        proprietari.setCognome(proprietariDTO.getCognome());
        if (proprietariDTO.getListaImmobiliDTO() != null) {
            proprietari.setListaImmobili(ConverterImmobile.converterListToEntityNoProp(proprietariDTO.getListaImmobiliDTO()));

        }
        return proprietari;
    }
    public static Proprietari converterToEntityXImmobili(ProprietariDTO proprietariDTO) {
        Proprietari proprietari = new Proprietari();

        proprietariDTO.setId(proprietariDTO.getId());
        proprietari.setNome(proprietariDTO.getNome());
        proprietari.setCognome(proprietariDTO.getCognome());

        return proprietari;
    }
    public static Proprietari converterToEntityXInsert(ProprietariDTO proprietariDTO) {
        Proprietari proprietari = new Proprietari();

        proprietari.setId(proprietariDTO.getId());
        proprietari.setNome(proprietariDTO.getNome());
        proprietari.setCognome(proprietariDTO.getCognome());

        return proprietari;
    }




}
