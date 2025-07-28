package com.develhope.co.biblioteca_prova.dto;

import java.time.LocalDateTime;

public class PrestitoPerGiornoDTO {
    private LocalDateTime giorno;
    private int numeroPrestiti;

    public PrestitoPerGiornoDTO(LocalDateTime giorno, int numeroPrestiti) {
        this.giorno = giorno;
        this.numeroPrestiti = numeroPrestiti;
    }

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
