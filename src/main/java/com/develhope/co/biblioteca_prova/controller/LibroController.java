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
    public Page<Libro> findAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
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

}
