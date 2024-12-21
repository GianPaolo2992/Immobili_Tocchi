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

    public static List<Annessi> convertListToEntityNoIMMBL(List<AnnessiDTO> listaAnnessiDTO){
        List<Annessi> listaAnnessi = new ArrayList<>();

        if (!listaAnnessiDTO.isEmpty()){

            for (AnnessiDTO annessoDTO : listaAnnessiDTO){
                Annessi annesso = new Annessi();
                annesso.setId(annessoDTO.getId());
                annesso.setTipo(annessoDTO.getTipo());
                annesso.setSuperficie(annessoDTO.getSuperficie());
                listaAnnessi.add(annesso);
            }
        }
        return listaAnnessi;
    }
}
