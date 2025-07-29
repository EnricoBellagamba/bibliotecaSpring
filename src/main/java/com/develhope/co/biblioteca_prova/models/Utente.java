package com.develhope.co.biblioteca_prova.models;

import com.develhope.co.biblioteca_prova.enums.RuoloUtenti;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

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

    @OneToOne(mappedBy = "utente", cascade = CascadeType.ALL)
    private FidelityCard fidelityCard;

    @JsonIgnore
    @OneToMany(mappedBy = "utente", fetch = FetchType.EAGER)
    private List<Prestito> prestiti;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private RuoloUtenti ruolo;

    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    private List<Vendita> vendite;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @NotNull
    @Column(nullable = false)
    private String password;

    public void setVendite(List<Vendita> vendite) {
        this.vendite = vendite;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public FidelityCard getFidelityCard() {
        return fidelityCard;
    }

    public void setFidelityCard(FidelityCard fidelityCard) {
        this.fidelityCard = fidelityCard;
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    public void setPrestiti(List<Prestito> prestiti) {
        this.prestiti = prestiti;
    }

    public List<Vendita> getVendite() {
        return vendite;
    }
}
