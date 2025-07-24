package com.develhope.co.biblioteca_prova.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
public class Carrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer numeroCopie;

    @ManyToOne
    private Libro libro;

    @ManyToOne
    @JsonIgnore
//    @Column(nullable = false) ??
    private Vendita vendita;


    // dobbiamo calcolarlo
    @Column(nullable = false)
   // @Positive(message = "Il prezzo deve essere positivo")??
    private Double prezzoPerCopia;

    // Getter e setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Vendita getVendita() {
        return vendita;
    }

    public void setVendita(Vendita vendita) {
        this.vendita = vendita;
    }

    public Double getPrezzoPerCopia() {
        return prezzoPerCopia;
    }

    public void setPrezzoPerCopia(Double prezzoPerCopia) {
        this.prezzoPerCopia = prezzoPerCopia;
    }

    public Integer getNumeroCopie() {
        return numeroCopie;
    }

    public void setNumeroCopie(Integer numeroCopie) {
        this.numeroCopie = numeroCopie;
    }
}
