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
}
