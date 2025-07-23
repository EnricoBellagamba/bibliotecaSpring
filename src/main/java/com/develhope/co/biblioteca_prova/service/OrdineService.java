package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.models.Acquisto;
import com.develhope.co.biblioteca_prova.models.Libro;
import com.develhope.co.biblioteca_prova.models.Ordine;
import com.develhope.co.biblioteca_prova.repository.AcquistoRepository;
import com.develhope.co.biblioteca_prova.repository.LibroConCopieRepository;
import com.develhope.co.biblioteca_prova.repository.LibroRepository;
import com.develhope.co.biblioteca_prova.repository.OrdineRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Transactional
    public Ordine salvaOrdine(Ordine ordine) {
        Ordine ordine_nuovo = ordineRepository.save(ordine);
        for (Acquisto acquisto : ordine.getAcquisti()) {
            Optional<Libro> libroOptional = libroRepo.findById(acquisto.getLibro().getIsbn());
            if (libroOptional.isPresent()){
                acquisto.setOrdine(ordine_nuovo);
                acquistoRepository.save(acquisto);
            }else{ throw new ValidationException("Libro non trovato con ISBN: " + acquisto.getLibro().getIsbn());

            }

        }
        return ordine_nuovo;
    }
}
