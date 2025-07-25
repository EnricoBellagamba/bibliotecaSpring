package com.develhope.co.biblioteca_prova.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class StatisticheDTO {

    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private int numeroPrestiti;
    private int numeroVendite;
    private BigDecimal fatturato;
    private BigDecimal spese;
    private BigDecimal vendite;
    private Map<LocalDate, Integer> prestitiPerGiorno;

    private StatisticheDTO() {
    }

    public StatisticheDTO(LocalDateTime dataInizio,
                          LocalDateTime dataFine,
                          int numeroPrestiti,
                          int numeroVendite,
                          BigDecimal fatturato,
                          BigDecimal spese,
                          BigDecimal vendite,
                          Map<LocalDate, Integer> prestitiPerGiorno) {

        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.numeroPrestiti = numeroPrestiti;
        this.numeroVendite = numeroVendite;
        this.fatturato = fatturato;
        this.spese = spese;
        this.vendite = vendite;
        this.prestitiPerGiorno = prestitiPerGiorno;
    }

    // Getters e Setters
    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public int getNumeroPrestiti() {
        return numeroPrestiti;
    }

    public void setNumeroPrestiti(int numeroPrestiti) {
        this.numeroPrestiti = numeroPrestiti;
    }

    public int getNumeroVendite() {
        return numeroVendite;
    }

    public void setNumeroVendite(int numeroVendite) {
        this.numeroVendite = numeroVendite;
    }

    public BigDecimal getFatturato() {
        return fatturato;
    }

    public void setFatturato(BigDecimal fatturato) {
        this.fatturato = fatturato;
    }

    public BigDecimal getSpese() {
        return spese;
    }

    public void setSpese(BigDecimal spese) {
        this.spese = spese;
    }

    public BigDecimal getVendite() {
        return vendite;
    }

    public void setVendite(BigDecimal vendite) {
        this.vendite = vendite;
    }

    public Map<LocalDate, Integer> getPrestitiPerGiorno() {
        return prestitiPerGiorno;
    }

    public void setPrestitiPerGiorno(Map<LocalDate, Integer> prestitiPerGiorno) {
        this.prestitiPerGiorno = prestitiPerGiorno;
    }


    // {
    //	"dataInizio" : "",
    //	"dataFine" : "",
    //	"numeroPrestiti" : 0,
    //	"numeroVendite" : 0,
    //	"fatturato" : 0,
    //	"spese" : 0,
    //	"prestitiPerGiorno" : {
    //	"2025-01-01" : 3,
    //	"2025-01-04" : 5,
    //	"2025-01-02" : 2,
    //	}
    //}

}
