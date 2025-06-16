package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.models.Libri;
import com.develhope.co.biblioteca_prova.repository.LibriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libri")
public class LibriController {
    @Autowired
    private LibriRepository libriRepo;

    @GetMapping
    public Page<Libri> findAll(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                               @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        //paginazione
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return libriRepo.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Libri findById(@PathVariable("id") String isbn) {
        Optional<Libri> l = libriRepo.findById(isbn);
        if(l.isPresent()) {
            return l.get();
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(404));
    }

    //create+update


}
