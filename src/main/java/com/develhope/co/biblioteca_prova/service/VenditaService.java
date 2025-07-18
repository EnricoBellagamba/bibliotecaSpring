package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.exceptions.DataValidationException;
import com.develhope.co.biblioteca_prova.models.*;
import com.develhope.co.biblioteca_prova.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
//Se l'utente è provvisto di carta fedeltà, uno sconto del 10% viene applicato automaticamente quando
// l'importo dell'acquisto è superiore a 50€.
@Service
public class  VenditaService {

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
    @Autowired
    private LibroConCopieRepository libroConCopieRepository;


    // aggiungi controllo per verificare se le copie esistono prima di venderle
    public Vendita salvaVendita(Vendita v, Double scontoOperatore) {

        if (v.getCarrello() == null || v.getCarrello().isEmpty()) {
            throw new DataValidationException("Il carrello non puà essere vuoto");
        }
        for (Carrello c : v.getCarrello()) {
            String isbn = c.getLibro().getIsbn();
            if (isbn == null || isbn.isBlank()) {
                throw new DataValidationException("ISBN del libro mancante o vuoto");
            }

            Optional<LibroConCopie> optionalLibro = libroConCopieRepository.findById(isbn);

            if (optionalLibro.isEmpty()) {
                throw new DataValidationException("Libro con ISBN : " + isbn + " non trova copie nel database");
            }

            LibroConCopie libro = optionalLibro.get();

            if (libro.getCopieDisponibili() < c.getNumeroCopie()) {
                throw new DataValidationException("Copie insufficienti per il libro con ISBN " + isbn);
            }
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



        Vendita vendita = venditaRepo.save(v);

        double sconto = fidelityCardService.calcolaSconto(vendita);


        if (scontoOperatore != null && scontoOperatore> sconto) {
            if (scontoOperatore < 0 || scontoOperatore > 1) {
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
