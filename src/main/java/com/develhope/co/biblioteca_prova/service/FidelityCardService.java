package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.models.FidelityCard;
import com.develhope.co.biblioteca_prova.models.Libro;
import com.develhope.co.biblioteca_prova.models.Utente;
import org.springframework.stereotype.Service;

@Service
public class FidelityCardService {

    public double calcolaPrezzoFinale(Utente utente, Libro libro) {
        double prezzoLibro = libro.getPrezzo();

        FidelityCard fidelityCard = utente.getFidelityCard();

        if (fidelityCard != null) {
            double sconto = fidelityCard.getPrezzo();
//            double prezzoScontato = prezzoLibro - sconto;
            return prezzoLibro - sconto;
        } else {
            return prezzoLibro;
        }
    }

}
