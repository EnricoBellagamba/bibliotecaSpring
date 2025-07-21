package com.develhope.co.biblioteca_prova.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Vendita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false)
    @PastOrPresent(message = "La data della vendita non può essere nel futuro")
    private LocalDateTime dataVendita;

    @OneToMany(mappedBy = "vendita")
    //    @Column(nullable = false)??
    private List<Carrello> carrello;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Utente utente;

    // Getter e setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataVendita() {
        return dataVendita;
    }

    public void setDataVendita(LocalDateTime dataVendita) {
        this.dataVendita = dataVendita;
    }

    public List<Carrello> getCarrello() {
        return carrello;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public double getPrezzoFinale() {
        double totale = 0;
        for (Carrello c : carrello) {
            totale += c.getPrezzoPerCopia();
        }
        return totale;
    }

    public double getValoreTotale() {
        double totale = 0;
        for (Carrello c : carrello) {
            totale += c.getLibro().getPrezzo()*c.getNumeroCopie();
        }
        return totale;
    }

}
