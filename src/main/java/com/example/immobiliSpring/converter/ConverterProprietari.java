package com.example.immobiliSpring.converter;

import com.example.immobiliSpring.DTO.ProprietariDTO;
import com.example.immobiliSpring.entity.Proprietari;

public class ConverterProprietari {

    public static ProprietariDTO converterToDTO(Proprietari proprietari) {
        ProprietariDTO proprietariDTO = new ProprietariDTO();

        proprietariDTO.setId(proprietari.getId());
        proprietariDTO.setNome(proprietari.getNome());
        proprietariDTO.setCognome(proprietari.getCognome());
        proprietariDTO.setListaImmobiliDTO(ConverterImmobile.converterListToDTONoProp(proprietari.getListaImmobili()));
        return proprietariDTO;
    }
}
