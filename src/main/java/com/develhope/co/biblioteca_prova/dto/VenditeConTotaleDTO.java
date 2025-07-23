package com.develhope.co.biblioteca_prova.dto;

import com.develhope.co.biblioteca_prova.models.Vendita;

import java.util.List;

public class VenditeConTotaleDTO {
    private List<Vendita> vendite;

    public VenditeConTotaleDTO(List<Vendita> vendite){
        this.vendite = vendite;
    }

    public List<Vendita> getVendite() {
        return vendite;
    }

    public double getTotale() {
        double totale = 0;
        for (Vendita v : vendite) {
            totale += v.getValoreTotale();
        }
        return totale;
    }
}
