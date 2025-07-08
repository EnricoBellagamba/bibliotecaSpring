package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.models.Libro;
import com.develhope.co.biblioteca_prova.models.Prestito;
import com.develhope.co.biblioteca_prova.models.Utente;
import com.develhope.co.biblioteca_prova.repository.LibroRepository;
import com.develhope.co.biblioteca_prova.repository.PrestitoRepository;
import com.develhope.co.biblioteca_prova.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PrestitoService {
    @Autowired
    private PrestitoRepository prestitoRepo;

    @Autowired
    private UtenteRepository utenteRepo;

    @Autowired
    private LibroRepository libroRepo;

    // POST /prestito/{id_prestito}/{isbn_libro}
    // public Prestito create(Utente u, Libro l){}


    // creaPrestito() -> controlli su utente e libro
    public Prestito save(Prestito prestito) {
        Optional<Utente> utente = utenteRepo.findById(prestito.getUtente().getId());

        // Un utente non può avere più di 5 prestiti attivi
        // Un utente non può tenere se ha già un libro non restituito per più di 60gg

        if (utente.isEmpty())
            throw new DataIntegrityViolationException("Errore di integrità, l'utente non esiste ");

        Optional<Libro> libro = libroRepo.findById(prestito.getLibro().getIsbn());
        if (libro.isEmpty())
            throw new DataIntegrityViolationException("Errore di integrità, il libro non esiste ");
        prestito.setDataPrestito(LocalDateTime.now());
        return prestitoRepo.save(prestito);
    }
}
