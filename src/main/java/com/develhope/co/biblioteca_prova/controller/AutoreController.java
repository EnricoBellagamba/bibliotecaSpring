package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.models.Autore;
import com.develhope.co.biblioteca_prova.models.Libro;
import com.develhope.co.biblioteca_prova.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class AutoreController {

    @Autowired
    private AutoreRepository autoreRepo;

    @GetMapping
    public Page<Autore> findAll(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        if (pageNumber < 0 ) {
            pageNumber = 0;
        }
        if (pageSize < 1 || pageSize > 100) {//if separati
            pageSize = 10;
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return autoreRepo.findAll(pageable);


    }

    @GetMapping("/{id}")
    public Autore findById(@PathVariable("id") Integer id) {
        Optional<Autore> a = autoreRepo.findById(id);
        if (a.isPresent()) {
            return a.get();
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(404));
    }

    @PostMapping
    public Autore save(@RequestBody Autore autore) {
        if (autore.getId() == null || autore.getNome() == null ){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Dati on validi");
        }
        return autoreRepo.save(autore);
    }
}
