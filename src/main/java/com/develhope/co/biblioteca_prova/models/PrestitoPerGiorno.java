package com.develhope.co.biblioteca_prova.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Entity
@Immutable
public class PrestitoPerGiorno {
    @Id
    private LocalDateTime giorno;
    private int numeroPrestiti;

    // Getter e setter
    public LocalDateTime getGiorno() {
        return giorno;
    }

    public void setGiorno(LocalDateTime giorno) {
        this.giorno = giorno;
    }

    public int getNumeroPrestiti() {
        return numeroPrestiti;
    }

    public void setNumeroPrestiti(int numeroPrestiti) {
        this.numeroPrestiti = numeroPrestiti;
    }
}
