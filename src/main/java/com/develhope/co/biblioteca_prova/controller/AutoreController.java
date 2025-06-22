package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.models.Autore;
import com.develhope.co.biblioteca_prova.models.Libro;
import com.develhope.co.biblioteca_prova.repository.AutoreRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<APIResponse> findById(@PathVariable("id") Integer id) {
        Optional<Autore> a = autoreRepo.findById(id);
        if (a.isPresent()) {
            APIResponse apiResponse = new APIResponse(a.get());
            return ResponseEntity.ok(apiResponse);
        }

        String message = "Autore non trovato ! ";
        APIResponse apiResponse2 = new APIResponse(message);

        return ResponseEntity.status(404).body(apiResponse2);
    }

    @PostMapping
    public ResponseEntity<APIResponse> save(@Valid @RequestBody Autore autore, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            APIResponse apiResponse = new APIResponse(bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(apiResponse);
        }
        APIResponse apiResponse = new APIResponse(autoreRepo.save(autore));
        return ResponseEntity.ok(apiResponse);
    }
}
