package com.develhope.co.biblioteca_prova.models;

import com.develhope.co.biblioteca_prova.enums.RuoloUtenti;
import jakarta.persistence.*;

@Entity
public class Utenti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100)
    private String cognome;

    @Enumerated(EnumType.STRING)
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
