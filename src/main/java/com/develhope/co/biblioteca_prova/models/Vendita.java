package com.develhope.co.biblioteca_prova.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Vendita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime dataVendite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataVendite() {
        return dataVendite;
    }

    public void setDataVendite(LocalDateTime dataVendite) {
        this.dataVendite = dataVendite;
    }
}
