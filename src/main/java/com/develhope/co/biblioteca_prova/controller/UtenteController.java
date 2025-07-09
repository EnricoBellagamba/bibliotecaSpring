package com.develhope.co.biblioteca_prova.controller;
import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.dto.PaginationDTO;
import com.develhope.co.biblioteca_prova.models.Utente;
import com.develhope.co.biblioteca_prova.models.Vendita;
import com.develhope.co.biblioteca_prova.repository.UtenteRepository;
import com.develhope.co.biblioteca_prova.utils.PaginationUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteRepository utenteRepo;

    @GetMapping
    public ResponseEntity<APIResponse> findAll(PaginationDTO paginationDTO){

        Pageable pageable = PaginationUtils.createPage(paginationDTO);
        return ResponseEntity.ok(new APIResponse(utenteRepo.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findById(
            @PathVariable("id") Integer id
    ) {
        Optional<Utente> opt = utenteRepo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.badRequest().body(new APIResponse("Vendita non trovata"));
        }
        return ResponseEntity.ok(new APIResponse(opt.get()));
    }

    @PostMapping
    public ResponseEntity<APIResponse> save(
            @Valid @RequestBody Utente utente,
            BindingResult br) {
        if (br.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(new APIResponse(br.getAllErrors()));
        }
        return ResponseEntity.ok(new APIResponse(utenteRepo.save(utente)));
    }

    @GetMapping("/{id}/prestiti")
    public ResponseEntity<APIResponse> findPrestitiUtente(@PathVariable("id") Integer id) {

        Optional<Utente> utente = utenteRepo.findById(id);

        if (utente.isEmpty()) {
            return new ResponseEntity<>(new APIResponse("Utente non trovato"), HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(new APIResponse(utente.get().getPrestiti()));
        }

    }
}
