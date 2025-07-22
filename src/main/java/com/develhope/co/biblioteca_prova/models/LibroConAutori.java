package com.develhope.co.biblioteca_prova.models;

import com.develhope.co.biblioteca_prova.enums.GeneriLibri;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;


@org.springframework.data.annotation.Immutable//sola lettura
@Immutable
@Entity
public class LibroConAutori {
    @Id
    private String isbn;

    private String titolo;

    private Integer annoPubblicazione;

    private double prezzo;

    @Enumerated(EnumType.STRING)
    private GeneriLibri genere;

    @Lob
    private String descrizione;

    private String autorePrincipale;


    public String getAutorePrincipale() {
        return autorePrincipale;
    }

    public void setAutorePrincipale(String autorePrincipale) {
        this.autorePrincipale = autorePrincipale;
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
