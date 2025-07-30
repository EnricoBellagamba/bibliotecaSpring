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
    private List<Articolo> articolo;

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

    public void setArticolo(List<Articolo> articolo) {
        this.articolo = articolo;
    }

    public void setDataVendita(LocalDateTime dataVendita) {
        this.dataVendita = dataVendita;
    }

    public List<Articolo> getArticolo() {
        return articolo;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

//    public double getPrezzoFinale() {
//        double totale = 0;
//        for (Carrello c : carrello) {
//            totale += c.getPrezzoPerCopia();
//        }
//        return totale;
//    }

    public double getValoreTotale() {
        double totale = 0;
        for (Articolo a : articolo) {
            if (a.getPrezzoPerCopia() != null && a.getNumeroCopie() != null) {
                totale += a.getPrezzoPerCopia() * a.getNumeroCopie();
            }
        }
        return totale;
    }

}
