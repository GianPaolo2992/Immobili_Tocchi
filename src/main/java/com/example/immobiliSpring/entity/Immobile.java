package com.example.immobiliSpring.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "immobile")
public class Immobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idi")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "xidp")
    private Proprietari proprietari;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "vani")
    private Integer vani;

    @Column(name = "costo")
    private Integer costo;

    @Column(name = "superfice")
    private Integer superfice;

    @Column(name = "anno")
    private Integer anno;
    @OneToMany(
            mappedBy = ("immobile")
    )
    private List<Annessi> listaAnnessi;

    public Immobile(){}


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

    public Proprietari getProprietari() {
        return proprietari;
    }

    public void setProprietari(Proprietari proprietari) {
        this.proprietari = proprietari;
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

    public List<Annessi> getListaAnnessi() {
        return listaAnnessi;
    }

    public void setListaAnnessi(List<Annessi> listaAnnessi) {
        this.listaAnnessi = listaAnnessi;
    }
}
