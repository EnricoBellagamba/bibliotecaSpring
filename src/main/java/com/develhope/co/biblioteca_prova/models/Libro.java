package com.develhope.co.biblioteca_prova.models;

import com.develhope.co.biblioteca_prova.enums.GeneriLibri;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

//@Table(name = "")
@Entity
public class Libro {
    @Id
    @Column(length = 20)
    @NotBlank(message = "L'ISBN è obbligatorio")
    private String isbn;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Il titolo non può essere vuoto")
    private String titolo;

    @Column
    @Min(value = 1)
    private Integer annoPubblicazione;

    @Column(nullable = false)
    @Positive
    @DecimalMin(value = "0.0")
    private double prezzo;

    @Enumerated(EnumType.STRING)
    private GeneriLibri genere;

    @Lob
    private String descrizione;

    @AssertTrue(message = "L'anno di pubblicazione non può essere nel futuro")
    public boolean isAnnoPubblicazioneValido() {
        if (annoPubblicazione == null) {
            return true;
        } else {
            return annoPubblicazione <= LocalDateTime.now().getYear();
        }
    }

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
