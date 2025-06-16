package com.develhope.co.biblioteca_prova.models;

import com.develhope.co.biblioteca_prova.enums.GeneriLibri;
import jakarta.persistence.*;

@Entity
public class Libro {
    @Id
    @Column(length = 20)
    private String isbn;

    @Column(length = 100, nullable = false)
    private String titolo;

    @Column
    private Integer annoPubblicazione;

    @Column(nullable = false)
    private double prezzo;

    @Enumerated(EnumType.STRING)
    private GeneriLibri genere;

    @Column(length = 200)
    private String descrizione;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(Integer annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public GeneriLibri getGenere() {
        return genere;
    }

    public void setGenere(GeneriLibri genere) {
        this.genere = genere;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
