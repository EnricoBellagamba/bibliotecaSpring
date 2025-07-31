package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.enums.RuoloUtenti;
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

        if (vendita.getValoreTotale().doubleValue() > 50 && vendita.getUtente().getFidelityCard() != null) {
            sconto = Math.max(sconto, 0.1);
        }
        if (vendita.getUtente().getRuolo() == RuoloUtenti.OPERATORE) {
            sconto = Math.max(sconto, 0.05);
        }

        return sconto;
    }
}
