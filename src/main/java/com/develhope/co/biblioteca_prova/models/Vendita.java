package com.develhope.co.biblioteca_prova.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private List<Carrello> carrello;

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

//    public double getPrezzoTotale() {
//        double totale = 0;
//        for (Carrello c : carrello) {
//            totale += c.getPrezzoTotale();
//        }
//        return totale;
//    }


}
