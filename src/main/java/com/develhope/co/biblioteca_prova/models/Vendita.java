package com.develhope.co.biblioteca_prova.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private List<Articolo> articoli;

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

    public void setArticoli(List<Articolo> articoli) {
        this.articoli = articoli;
    }

    public void setDataVendita(LocalDateTime dataVendita) {
        this.dataVendita = dataVendita;
    }

    public List<Articolo> getArticoli() {
        return articoli;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    /**
     * Questo metodo restituisce il valore totale della vendita senza lo sconto applicato
     *
     * @return
     */
    public BigDecimal getValoreTotale() {
        double totale = 0;
        for (Articolo a : articoli) {
            totale += a.getLibro().getPrezzo() * a.getNumeroCopie();
        }
        return new BigDecimal(totale).setScale(2, RoundingMode.DOWN);
    }

    /**
     * @return
     */
    public BigDecimal getValoreTotScontato() {
        double totale = 0;
        for (Articolo a : articoli) {
            totale += a.getPrezzoPerCopia().doubleValue() * a.getNumeroCopie();
        }
        return new BigDecimal(totale).setScale(2, RoundingMode.DOWN);

    }

}
