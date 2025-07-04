package com.develhope.co.biblioteca_prova.models;

import com.develhope.co.biblioteca_prova.enums.GeneriLibri;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@org.springframework.data.annotation.Immutable//sola lettura
//@Table(schema = "debugSchema", name = "libroConCopieView")//per non far creare la tabella
public class LibroConCopie {

    @Id
    private String id;

    private String titolo;

    private Integer annoPubblicazione;

    private double prezzo;

    @Enumerated(EnumType.STRING)
    private GeneriLibri genere;

    @Lob
    private String descrizione;

    private Integer copiePrestate;

    private Integer copieAcquistate;

    private Integer copieDisponibili;

    public Integer getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(Integer annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public Integer getCopieAcquistate() {
        return copieAcquistate;
    }

    public void setCopieAcquistate(Integer copieAcquistate) {
        this.copieAcquistate = copieAcquistate;
    }

    public Integer getCopieDisponibili() {
        return copieDisponibili;
    }

    public void setCopieDisponibili(Integer copieDisponibili) {
        this.copieDisponibili = copieDisponibili;
    }

    public Integer getCopiePrestate() {
        return copiePrestate;
    }

    public void setCopiePrestate(Integer copiePrestate) {
        this.copiePrestate = copiePrestate;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public GeneriLibri getGenere() {
        return genere;
    }

    public void setGenere(GeneriLibri genere) {
        this.genere = genere;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
}
