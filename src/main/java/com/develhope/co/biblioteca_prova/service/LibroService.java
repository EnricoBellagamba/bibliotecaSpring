package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.exceptions.DataValidationException;
import com.develhope.co.biblioteca_prova.models.Libro;
import com.develhope.co.biblioteca_prova.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepo;

    public Libro save(Libro libro){
        if(libro.getPrezzo()<0){
            throw new DataValidationException("Prezzo non valido ");
        }
        return libroRepo.save(libro);
    }
}
