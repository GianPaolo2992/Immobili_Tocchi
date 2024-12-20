package com.example.immobiliSpring.DTO;

import com.example.immobiliSpring.entity.Annessi;
import com.example.immobiliSpring.entity.Proprietari;
import jakarta.persistence.*;

import java.util.List;

public class ImmobileDTO {


    private Integer id;


    private ProprietariDTO proprietariDTO;


    private String tipo;


    private Integer vani;


    private Integer costo;

    private Integer superfice;


    private Integer anno;

    private List<AnnessiDTO> listaAnnessiDTO;


    public ImmobileDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ProprietariDTO getProprietariDTO() {
        return proprietariDTO;
    }

    public void setProprietariDTO(ProprietariDTO proprietariDTO) {
        this.proprietariDTO = proprietariDTO;
    }

    public Integer getVani() {
        return vani;
    }

    public void setVani(Integer vani) {
        this.vani = vani;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Integer getSuperfice() {
        return superfice;
    }

    public void setSuperfice(Integer superfice) {
        this.superfice = superfice;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public List<AnnessiDTO> getListaAnnessiDTO() {
        return listaAnnessiDTO;
    }

    public void setListaAnnessiDTO(List<AnnessiDTO> listaAnnessiDTO) {
        this.listaAnnessiDTO = listaAnnessiDTO;
    }
}
