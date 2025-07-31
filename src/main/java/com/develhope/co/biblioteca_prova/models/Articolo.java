package com.develhope.co.biblioteca_prova.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class Articolo {
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
    @Column(nullable = false, scale = 2, precision = 10)
   // @Positive(message = "Il prezzo deve essere positivo")??
    private BigDecimal prezzoPerCopia;

    // Getter e setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroCopie() {
        return numeroCopie;
    }

    public void setNumeroCopie(Integer numeroCopie) {
        this.numeroCopie = numeroCopie;
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

    public BigDecimal getPrezzoPerCopia() {
        return prezzoPerCopia.setScale(2, RoundingMode.DOWN);
    }

    public void setPrezzoPerCopia(BigDecimal prezzoPerCopia) {
        this.prezzoPerCopia = prezzoPerCopia;
    }
}
