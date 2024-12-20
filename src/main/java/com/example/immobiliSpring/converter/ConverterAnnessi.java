package com.example.immobiliSpring.converter;

import com.example.immobiliSpring.DTO.AnnessiDTO;
import com.example.immobiliSpring.entity.Annessi;

import java.util.ArrayList;
import java.util.List;

public class ConverterAnnessi {

    public static List<AnnessiDTO> convertListToDTONoIMMBL(List<Annessi> listaAnnessi){
        List<AnnessiDTO> listaAnnessiDTO = new ArrayList<>();

        if (!listaAnnessi.isEmpty()){

            for (Annessi annesso : listaAnnessi){
                AnnessiDTO annessoDTO = new AnnessiDTO();
                annessoDTO.setId(annesso.getId());
                annessoDTO.setTipo(annesso.getTipo());
                annessoDTO.setSuperficie(annesso.getSuperficie());
                listaAnnessiDTO.add(annessoDTO);
            }
        }
        return listaAnnessiDTO;
    }
}
