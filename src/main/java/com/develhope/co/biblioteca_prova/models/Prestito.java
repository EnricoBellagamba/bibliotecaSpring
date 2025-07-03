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




}
