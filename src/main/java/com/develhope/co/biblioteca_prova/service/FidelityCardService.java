package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.models.FidelityCard;
import com.develhope.co.biblioteca_prova.models.Libro;
import com.develhope.co.biblioteca_prova.models.Utente;
import com.develhope.co.biblioteca_prova.models.Vendita;
import org.springframework.stereotype.Service;

@Service
public class FidelityCardService {

    public double calcolaSconto(Vendita vendita) {
        double sconto = 0;
        System.out.println("valoreTotale: " + vendita.getValoreTotale());
        System.out.println("fidelitycard: " + vendita.getUtente().getFidelityCard());
        if (vendita.getValoreTotale()> 50 && vendita.getUtente().getFidelityCard() != null) {
            sconto = 0.1;
        }
        return sconto;
    }
    //scrivere metodo dove si calcola lo sconto di un ordine sopra i 50 euro e un'altro dove l'operatore setta lo sconto
}
