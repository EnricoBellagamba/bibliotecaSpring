package com.develhope.co.biblioteca_prova.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Prestito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dataPrestito;

    @Column
    private LocalDateTime dataRestituzione;

    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private Utente utente;

    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private Libro libro;

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDataPrestito() {
        return dataPrestito;
    }

    public void setDataPrestito(LocalDateTime dataPrestito) {
        this.dataPrestito = dataPrestito;
    }

    public LocalDateTime getDataRestituzione() {
        return dataRestituzione;
    }

    public void setDataRestituzione(LocalDateTime dataRestituzione) {
        this.dataRestituzione = dataRestituzione;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
