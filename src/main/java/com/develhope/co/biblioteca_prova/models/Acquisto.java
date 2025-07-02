package com.develhope.co.biblioteca_prova.models;

import jakarta.persistence.*;

@Entity
public class Acquisto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numCopie;

    private Double prezzoPerCopia;

    @ManyToOne
    private  Libro libro;

    @ManyToOne
    private Ordine ordine;

    public Integer getId() {
        return id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Integer getNumCopie() {
        return numCopie;
    }

    public void setNumCopie(Integer numCopie) {
        this.numCopie = numCopie;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public Double getPrezzoPerCopia() {
        return prezzoPerCopia;
    }

    public void setPrezzoPerCopia(Double prezzoPerCopia) {
        this.prezzoPerCopia = prezzoPerCopia;
    }
}


