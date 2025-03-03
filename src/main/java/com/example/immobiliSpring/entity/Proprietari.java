package com.example.immobiliSpring.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "proprietari")

public class Proprietari {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idp")
    private Integer id;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "nome")
    private String nome;
    @OneToMany(
            mappedBy = "proprietari",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
    private List<Immobile> listaImmobili;

    public  Proprietari(){}
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

    public List<Immobile> getListaImmobili() {
        return listaImmobili;
    }

    public void setListaImmobili(List<Immobile> listaImmobili) {
        this.listaImmobili = listaImmobili;
    }


}
