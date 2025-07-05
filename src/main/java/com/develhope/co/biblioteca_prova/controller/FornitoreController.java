package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.dto.PaginationDTO;
import com.develhope.co.biblioteca_prova.models.Fornitore;
import com.develhope.co.biblioteca_prova.repository.FornitoreRepository;
import com.develhope.co.biblioteca_prova.service.FornitoreService;
import com.develhope.co.biblioteca_prova.utils.PaginationUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/fornitori")
public class FornitoreController {

    @Autowired
    private FornitoreRepository fornitoriRepo;

    @Autowired
    private FornitoreService fornitoreService;
    @GetMapping
    public ResponseEntity<APIResponse> findAll(PaginationDTO paginationDTO){

        Pageable pageable = PaginationUtils.createPage(paginationDTO);
        APIResponse ar = new APIResponse(fornitoriRepo.findAll(pageable));
        return ResponseEntity.ok(ar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findById(@PathVariable Integer id){
        Optional<Fornitore> opt = fornitoriRepo.findById(id);
        if (opt.isPresent()){
            return ResponseEntity.ok(new APIResponse(opt.get()));
        }
        return ResponseEntity.status(404).body(new APIResponse("Fornitore non trovato"));
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse> findByName(PaginationDTO paginationDTO, @RequestParam String nome){

        Pageable pageable = PaginationUtils.createPage(paginationDTO);
        APIResponse ar = new APIResponse(fornitoriRepo.findByNomeContains(nome, pageable));
        return ResponseEntity.ok(ar);
    }

    @PostMapping
    public ResponseEntity<APIResponse> save(@Valid @RequestBody Fornitore fornitore, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new APIResponse("Name can not be null"));
        }

        Fornitore saved = fornitoriRepo.save(fornitore);
        return ResponseEntity.ok(new APIResponse(saved));
    }


}
