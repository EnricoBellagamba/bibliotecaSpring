package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.models.Acquisto;
import com.develhope.co.biblioteca_prova.repository.AcquistoRepository;
import com.develhope.co.biblioteca_prova.repository.LibroRepository;
import com.develhope.co.biblioteca_prova.repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AcquistoService {
    @Autowired
    private LibroRepository libroRepo;

    @Autowired
    private AcquistoRepository acquistoRepo;

    @Autowired
    private OrdineRepository ordineRepo;

    public APIResponse salvaAcquisto(Acquisto acquisto){

        if(!ordineRepo.existsById(acquisto.getOrdine().getId())){
            return new APIResponse(404,"Ordine non trovato",false);
        }
        if(!libroRepo.existsById(acquisto.getLibro().getIsbn())){
            return new APIResponse(404,"Libro non trovato",false);
        }
        if (acquisto.getNumCopie() <= 0 || acquisto.getPrezzoPerCopia() < 0){
            return new APIResponse(400,"Numero copie o prezzo per copia non valido",false);
        }
        return new APIResponse(acquistoRepo.save(acquisto));
    }
}
