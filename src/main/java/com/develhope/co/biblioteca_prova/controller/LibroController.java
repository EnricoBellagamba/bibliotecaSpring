package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.models.Libro;
import com.develhope.co.biblioteca_prova.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/libri")
public class LibroController {
    @Autowired
    private LibroRepository libriRepo;

    @GetMapping
    public Page<Libro> findAll(@RequestParam(required = false, defaultValue = "1") Integer pageNumber, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (pageSize > 100 ) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Page size cannot exceed 100");
        }
        return libriRepo.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Libro findById(@PathVariable("id") String isbn) {
        Optional<Libro> l = libriRepo.findById(isbn);
        if (l.isPresent()) {
            return l.get();
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(404));
    }

    @PostMapping
    public Libro save(@RequestBody Libro libro) {
        if (libro.getIsbn() == null || libro.getTitolo() == null || libro.getAnnoPubblicazione() == null || libro.getPrezzo() <= 0) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Dati on validi");
        }
        return libriRepo.save(libro);
    }

}
