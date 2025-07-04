package com.develhope.co.biblioteca_prova.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

@Entity
public class Acquisto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(value = 1)
    private Integer numCopie;

    @DecimalMin(value = "0.01")
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


