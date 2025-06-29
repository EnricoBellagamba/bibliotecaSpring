package com.develhope.co.biblioteca_prova.models;

import com.develhope.co.biblioteca_prova.enums.RuoloUtenti;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100)
    private String cognome;

    @Column
    private boolean fidelityCard ;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private RuoloUtenti ruolo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public RuoloUtenti getRuolo() {
        return ruolo;
    }

    public void setRuolo(RuoloUtenti ruolo) {
        this.ruolo = ruolo;
    }
}
