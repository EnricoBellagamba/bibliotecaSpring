package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.exceptions.DataValidationException;
import com.develhope.co.biblioteca_prova.models.Carrello;
import com.develhope.co.biblioteca_prova.models.Libro;
import com.develhope.co.biblioteca_prova.models.Utente;
import com.develhope.co.biblioteca_prova.models.Vendita;
import com.develhope.co.biblioteca_prova.repository.CarrelloRepository;
import com.develhope.co.biblioteca_prova.repository.LibroRepository;
import com.develhope.co.biblioteca_prova.repository.UtenteRepository;
import com.develhope.co.biblioteca_prova.repository.VenditaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VenditaService {

    @Autowired
    private VenditaRepository venditaRepo;

    @Autowired
    private UtenteRepository utenteRepo;

    @Autowired
    private LibroRepository libroRepo;

    @Autowired
    private CarrelloRepository carrelloRepo;

    @Autowired
    private FidelityCardService fidelityCardService;

    public Vendita salvaVendita(Vendita v, Double scontoOperatore){
        if (v.getCarrello() == null || v.getCarrello().isEmpty()) {
            throw new DataValidationException("Il carrello non puà essere vuoto");
        }
        Integer utenteId = v.getUtente().getId();
        if (utenteId == null) {
           throw new DataValidationException("L'utente non esiste");
        }
        Optional<Utente> optionalUtente = utenteRepo.findById(utenteId);
        if (optionalUtente.isEmpty()) {
            throw new DataValidationException("L'utente è vuoto");
        }
        v.setUtente(optionalUtente.get());
        for (Carrello c : v.getCarrello()) {
            Optional<Libro> opt = libroRepo.findById(c.getLibro().getIsbn());
            if (opt.isEmpty()) {
                throw new DataValidationException("Libro non presente nel db");
            }else{
                c.setLibro(opt.get());
            }
        }

        Vendita vendita = venditaRepo.save(v);
        double sconto = fidelityCardService.calcolaSconto(vendita);

        if(scontoOperatore != null ){
            if (scontoOperatore < 0 || scontoOperatore > 1){
                throw new DataValidationException("lo sconto deve essere compreso tra 0 e 1");
            }
            sconto = scontoOperatore;
        }

        for (Carrello c : v.getCarrello()) {
            c.setVendita(vendita);
            double prezzoListino = c.getLibro().getPrezzo();
            double prezzoScontato = prezzoListino * (1 - sconto);
            System.out.println("prezzoscontato:" + prezzoScontato);
            System.out.println("prezzolistino:" + prezzoListino);
            System.out.println("sconto:" + sconto);
            c.setPrezzoPerCopia(prezzoScontato);
            carrelloRepo.save(c);
        }
       return vendita;
    }

}
