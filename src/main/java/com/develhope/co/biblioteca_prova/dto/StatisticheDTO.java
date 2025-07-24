package com.develhope.co.biblioteca_prova.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class StatisticheDTO {

    private String dataInizio;
    private String dataFine;
    private int numeroPrestiti;
    private int numeroVendite;
    private BigDecimal fatturato;
    private BigDecimal spese;
    private Map<LocalDate, Integer> prestitiPerGiorno;

    // Getters e Setters

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) {
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
