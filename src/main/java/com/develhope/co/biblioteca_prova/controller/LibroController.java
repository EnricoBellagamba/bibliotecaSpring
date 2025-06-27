package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.exceptions.DataValidationException;
import com.develhope.co.biblioteca_prova.models.Libro;
import com.develhope.co.biblioteca_prova.repository.LibroRepository;
import com.develhope.co.biblioteca_prova.service.LibroService;
import com.develhope.co.biblioteca_prova.utils.PaginationUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/libri")
public class LibroController {
    @Autowired
    private LibroRepository libriRepo;

    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<APIResponse> findAll(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                               @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        Pageable pageable = PaginationUtils.createPage(pageNumber, pageSize);
        return ResponseEntity.ok().body(new APIResponse(libriRepo.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findById(@PathVariable("id") String isbn) {
        Optional<Libro> l = libriRepo.findById(isbn);
        if (l.isPresent()) {
            return ResponseEntity.ok().body(new APIResponse(l.get()));
        }
        return ResponseEntity.status(404).body(new APIResponse("Libro non trovato"));
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse> searchAll(@RequestParam String titolo,
                                                 @RequestParam Integer pageNumber,
                                                 @RequestParam Integer pageSize){
    Pageable pageable = PaginationUtils.createPage(pageNumber, pageSize);

    Page<Libro> page = libriRepo.findByTitoloContains(titolo,pageable);
    return ResponseEntity.ok(new APIResponse(page));
    }

    @PostMapping
    public ResponseEntity<APIResponse> save(@Valid @RequestBody Libro libro, BindingResult bindingResult) {  //inserisco @Valid per la validazione
        if (bindingResult.hasErrors()) {
            APIResponse apiResponse = new APIResponse(bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(apiResponse);
        }
        try{
            return ResponseEntity.ok().body(new APIResponse(libriRepo.save(libro)));
        } catch(DataValidationException | DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(new APIResponse(e.getMessage()));
        }

    }

}
