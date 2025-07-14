package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.dto.PaginationDTO;
import com.develhope.co.biblioteca_prova.models.Prestito;
import com.develhope.co.biblioteca_prova.repository.PrestitoRepository;
import com.develhope.co.biblioteca_prova.service.PrestitoService;
import com.develhope.co.biblioteca_prova.utils.PaginationUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/prestiti")
public class PrestitoController {
    @Autowired
    private PrestitoRepository prestitoRepo;
    @Autowired
    private PrestitoService prestitoService;

    @GetMapping
    public ResponseEntity<APIResponse> findAll(
            PaginationDTO pagination,
            @RequestParam(required = false) LocalDate dataPrestito
    ) {
        Pageable pageable = PaginationUtils.createPage(pagination);
        if (dataPrestito != null) {
            Page<Prestito> page = prestitoRepo.findByDataPrestito(dataPrestito, pageable);
            return ResponseEntity.ok(new APIResponse(page));
        } else {
            return ResponseEntity.ok().body(new APIResponse(prestitoRepo.findAll(pageable)));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findById(@PathVariable("id") Integer id) {
        Optional<Prestito> p = prestitoRepo.findById(id);
        if (p.isPresent()) {
            return ResponseEntity.ok().body(new APIResponse(p.get()));
        }
        return ResponseEntity.status(404).body(new APIResponse("Prestito non trovato"));
    }

    //@PostMapping("/{libroIsbn}/{idUtente}")
    @PostMapping
    public ResponseEntity<APIResponse> save(@Valid @RequestBody Prestito prestito,
                                            // PathVariable libroIsbn
                                            // PathVariable idUtente
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new APIResponse(bindingResult.getAllErrors()));
        }
        try {
            return ResponseEntity.ok(new APIResponse(prestitoService.save(prestito)));
        } catch (DataIntegrityViolationException | InvalidDataAccessApiUsageException e) {
            return ResponseEntity.badRequest().body(new APIResponse(e.getMessage() + " " + e.getRootCause()));
        }
    }
}
