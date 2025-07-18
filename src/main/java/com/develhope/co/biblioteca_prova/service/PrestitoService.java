package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.exceptions.DataValidationException;
import com.develhope.co.biblioteca_prova.models.*;
import com.develhope.co.biblioteca_prova.repository.LibroConCopieRepository;
import com.develhope.co.biblioteca_prova.repository.LibroRepository;
import com.develhope.co.biblioteca_prova.repository.PrestitoRepository;
import com.develhope.co.biblioteca_prova.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrestitoService {
    @Autowired
    private PrestitoRepository prestitoRepo;

    @Autowired
    private UtenteRepository utenteRepo;

    @Autowired
    private LibroRepository libroRepo;

    @Autowired
    private LibroConCopieRepository libroConCopieRepository;

    // POST /prestito/{id_prestito}/{isbn_libro}
    // public Prestito create(Utente u, Libro l){}


    // creaPrestito() -> controlli su utente e libro
    public Prestito save(Prestito prestito) {
        Optional<Utente> utente = utenteRepo.findById(prestito.getUtente().getId());

            String isbn = prestito.getLibro().getIsbn();
            Optional<LibroConCopie> optionalLibro = libroConCopieRepository.findById(isbn);

        if (optionalLibro.isEmpty()) {
            throw new DataValidationException("Libro con ISBN " + isbn + " non trova copie nel database");
        }
            LibroConCopie libroConCopie = optionalLibro.get();

            if (libroConCopie.getCopieDisponibili() < 1) {
                throw new DataValidationException("Copie insufficienti per il libro con ISBN " + isbn);
            }



        if (utente.isEmpty())
            throw new DataIntegrityViolationException("Errore di integrità, l'utente non esiste");

        boolean controlloSuperato = controlloPrestitoNonRestituito(utente.get());
        boolean controlloSuperatoPrestitiAttivi = controlloNumeroPrestitiAttivi(utente.get());

        if (!controlloSuperato) {
            throw new DataIntegrityViolationException("L'utente ha un prestito attivo da più di 60 giorni");
        }

        if (!controlloSuperatoPrestitiAttivi) {
            throw new DataIntegrityViolationException("L'utente non può avere più di 5 prestiti attivi");
        }

        // Un utente non può avere più di 5 prestiti attivi
        // Un utente non può tenere se ha già un libro non restituito per più di 60gg

        Optional<Libro> libro = libroRepo.findById(prestito.getLibro().getIsbn());
        if (libro.isEmpty())
            throw new DataIntegrityViolationException("Errore di integrità, il libro non esiste ");
        prestito.setDataPrestito(LocalDateTime.now());
        return prestitoRepo.save(prestito);
    }

    private boolean controlloPrestitoNonRestituito(Utente utente) {

        List<Prestito> prestiti = utente.getPrestiti();

        for (Prestito p : prestiti) {
            if (p.getDataRestituzione() == null) {
                LocalDateTime oraAttuale = LocalDateTime.now();
                long numDays = Duration.between(p.getDataPrestito(), oraAttuale).toDays();
                if (numDays > 60) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean controlloNumeroPrestitiAttivi (Utente utente) {

        List<Prestito> prestiti = utente.getPrestiti();
        int count = 0;

        for (Prestito p : prestiti) {
            if (p.getDataRestituzione() == null) {
                count++;
            }
        }
        // return count > 5 ? false : true;

        return count <= 5;
    }
}
