package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.exceptions.DataValidationException;
import com.develhope.co.biblioteca_prova.models.Libro;
import com.develhope.co.biblioteca_prova.repository.LibroRepository;
import com.develhope.co.biblioteca_prova.service.LibroService;
import com.develhope.co.biblioteca_prova.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public Page<Libro> findAll(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                               @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        if (pageNumber < 0) {
            pageNumber = 0;

        }
        if (pageSize < 1 || pageSize > 100) {
            pageSize = 10;
        }
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

    @GetMapping("/search")
    public ResponseEntity<APIResponse> searchAll(@RequestParam String titolo,
                                                 @RequestParam Integer pageNumber,
                                                 @RequestParam Integer pageSize){
    Pageable pageable = PaginationUtils.createPage(pageNumber, pageSize);

    Page<Libro> page = libriRepo.findByTitoloContains(titolo,pageable);
    return  ResponseEntity.ok(new APIResponse(page));
    }

    @PostMapping
    public Libro save(@RequestBody Libro libro) {  //inserisco @Valid per la validazione
        try{
            return libroService.save(libro);
        } catch(DataValidationException | DataIntegrityViolationException e){
            //return new ResponseEntity<>(new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST )
            return null;
        }

    }

}
