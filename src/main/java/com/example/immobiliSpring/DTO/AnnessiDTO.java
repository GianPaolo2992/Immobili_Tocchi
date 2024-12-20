package com.example.immobiliSpring.DTO;

import com.example.immobiliSpring.entity.Immobile;
import jakarta.persistence.*;

public class AnnessiDTO {

    private Integer id;

    private ImmobileDTO immobileDTO;

    private String tipo;


    private Integer superficie;

    public AnnessiDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ImmobileDTO getImmobileDTO() {
        return immobileDTO;
    }

    public void setImmobileDTO(ImmobileDTO immobileDTO) {
        this.immobileDTO = immobileDTO;
    }

    public Integer getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Integer superficie) {
        this.superficie = superficie;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
