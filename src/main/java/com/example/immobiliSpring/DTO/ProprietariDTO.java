package com.example.immobiliSpring.DTO;

import com.example.immobiliSpring.entity.Immobile;
import jakarta.persistence.*;

import java.util.List;

public class ProprietariDTO {

    private Integer id;


    private String cognome;


    private String nome;

    private List<ImmobileDTO> listaImmobiliDTO;

    public  ProprietariDTO(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ImmobileDTO> getListaImmobiliDTO() {
        return listaImmobiliDTO;
    }

    public void setListaImmobiliDTO(List<ImmobileDTO> listaImmobiliDTO) {
        this.listaImmobiliDTO = listaImmobiliDTO;
    }

}
