package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.models.Acquisto;
import com.develhope.co.biblioteca_prova.models.Ordine;
import com.develhope.co.biblioteca_prova.repository.AcquistoRepository;
import com.develhope.co.biblioteca_prova.repository.LibroConCopieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AcquistoService {

    @Autowired
    private AcquistoRepository acquistoRepo;
    @Autowired
    private LibroConCopieRepository libroConCopieRepository;


    public Double getTotaleSpese(LocalDateTime dataInizio, LocalDateTime dataFine) {
        List<Acquisto> acquistiTotali = acquistoRepo.findAll();

        Double totale = 0.0;

        for (Acquisto a : acquistiTotali) {
            LocalDateTime dataOrdine = a.getOrdine().getDataOrdine();
            if (dataOrdine != null &&
                    !dataOrdine.isBefore(dataInizio) &&
                    !dataOrdine.isAfter(dataFine)) {
                if (a.getNumCopie() != null && a.getPrezzoPerCopia() != null) {
                    totale += a.getNumCopie() * a.getPrezzoPerCopia();
                }
            }
        }
        return totale;
    }
}
