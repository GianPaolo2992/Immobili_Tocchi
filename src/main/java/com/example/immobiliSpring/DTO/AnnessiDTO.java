package com.example.immobiliSpring.DTO;

import com.example.immobiliSpring.entity.Immobile;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id","tipo","superficie","immobileDTO"})
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
