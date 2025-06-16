package com.develhope.co.biblioteca_prova.models;

import com.develhope.co.biblioteca_prova.enums.Stato;
import com.develhope.co.biblioteca_prova.enums.TipoOrdini;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime dataOrdine;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Stato stato;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoOrdini tipi;

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

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public TipoOrdini getTipi() {
        return tipi;
    }

    public void setTipi(TipoOrdini tipi) {
        this.tipi = tipi;
    }
}
