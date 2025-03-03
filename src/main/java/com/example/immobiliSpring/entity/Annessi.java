package com.example.immobiliSpring.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

@Entity
@Table(name = "annessi")

public class Annessi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ida")
    private Integer id;


    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "xidi")
    private Immobile immobile;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "superfice")
    private Integer superficie;

    public Annessi() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Immobile getImmobile() {

        return immobile;
    }

    public void setImmobile(Immobile immobile) {
        this.immobile = immobile;
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
