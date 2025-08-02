package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.exceptions.DataValidationException;
import com.develhope.co.biblioteca_prova.models.*;
import com.develhope.co.biblioteca_prova.repository.*;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdineService {

    @Autowired
    private LibroRepository libroRepo;

    @Autowired
    private AcquistoRepository acquistoRepository;

    @Autowired
    private OrdineRepository ordineRepository;
    @Autowired
    private LibroConCopieRepository libroConCopieRepository;
    @Autowired
    private FornitoreRepository fornitoreRepo;
    @Autowired
    private AutoreRepository autoreRepo;

    @Transactional
    public Ordine salvaOrdine(Ordine ordine) {
        Ordine ordine_nuovo = ordineRepository.save(ordine);
        for (Acquisto acquisto : ordine.getAcquisti()) {
            Optional<Libro> libroOptional = libroRepo.findById(acquisto.getLibro().getIsbn());
            if (libroOptional.isPresent()) {
                acquisto.setOrdine(ordine_nuovo);
                validazioneAutori(acquisto);
                acquisto.setLibro(libroOptional.get());
                acquistoRepository.save(acquisto);
            } else {
                throw new ValidationException("Libro non trovato con ISBN: " + acquisto.getLibro().getIsbn());

            }
        }
        validazioneFornitore(ordine_nuovo);

        return ordine_nuovo;
    }

    void validazioneAutori(Acquisto acquisto){
        List<Autore> autori = autoreRepo.findByIsbn(acquisto.getLibro().getIsbn());
        for(Autore a : autori ){
            Optional<Autore> autoreOpt = autoreRepo.findById(a.getId());
            if (autoreOpt.isEmpty())
                throw new ValidationException("Autore non trovato con id: " + a.getId());
        }
        acquisto.getLibro().setAutori(autori);
    }

    void validazioneFornitore(Ordine ordine){
        Optional<Fornitore> fornitoreOpt = fornitoreRepo.findById(ordine.getFornitore().getId());
        if (fornitoreOpt.isPresent()){
            ordine.setFornitore(fornitoreOpt.get());
        }
        else
            throw new DataValidationException("Fornitore non trovato con id: "+ ordine.getFornitore().getId());
    }
}
