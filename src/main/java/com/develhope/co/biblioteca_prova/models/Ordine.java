package com.develhope.co.biblioteca_prova.models;

import com.develhope.co.biblioteca_prova.enums.StatoOrdine;
import com.develhope.co.biblioteca_prova.enums.TipoOrdine;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull
    @Column(nullable = false)
    private LocalDateTime dataOrdine;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoOrdine stato;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoOrdine tipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDateTime dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public StatoOrdine getStato() {
        return stato;
    }

    public void setStato(StatoOrdine stato) {
        this.stato = stato;
    }

    public TipoOrdine getTipo() {
        return tipo;
    }

    public void setTipo(TipoOrdine tipo) {
        this.tipo = tipo;
    }
}
