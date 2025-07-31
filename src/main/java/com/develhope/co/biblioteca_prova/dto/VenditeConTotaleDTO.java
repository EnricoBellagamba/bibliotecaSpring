package com.develhope.co.biblioteca_prova.dto;

import com.develhope.co.biblioteca_prova.models.Acquisto;
import com.develhope.co.biblioteca_prova.models.Vendita;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VenditeConTotaleDTO {
    private List<Vendita> vendite;

    public VenditeConTotaleDTO(List<Vendita> vendite) {
        this.vendite = vendite;
    }

    public List<Vendita> getVendite() {
        return vendite;
    }

    public BigDecimal getTotale() {
        BigDecimal totale = new BigDecimal(0);
        for (Vendita v : vendite) {
            totale = totale.add(v.getValoreTotale());
        }
        return totale;
    }
}
