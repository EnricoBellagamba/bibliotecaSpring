package com.develhope.co.biblioteca_prova.models;

import com.develhope.co.biblioteca_prova.enums.Stato;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Prestiti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime dataPrestito;

    @Column(nullable = false)
    private LocalDateTime dataRestituzione;

    @Column(nullable = false)
    private LocalDateTime dataScadenza;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Stato stato;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDateTime getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDateTime dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }
}
