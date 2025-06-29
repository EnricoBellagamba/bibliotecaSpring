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

@RestController
@RequestMapping("/fornitori")
public class FornitoreController {

    @Autowired
    private FornitoreRepository fornitoreRepo;

    @Autowired
    private FornitoreService fornitoreService;
    @GetMapping
    public ResponseEntity<APIResponse> getAll(PaginationDTO paginationDTO){

        Pageable pageable = PaginationUtils.createPage(paginationDTO);
        APIResponse ar = new APIResponse(fornitoreRepo.findAll(pageable));
        return ResponseEntity.ok(ar);
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse> getByName(PaginationDTO paginationDTO, @RequestParam String nome){

        Pageable pageable = PaginationUtils.createPage(paginationDTO);
        APIResponse ar = new APIResponse(fornitoreRepo.findByNomeContains(nome, pageable));
        return ResponseEntity.ok(ar);
    }

    @PostMapping
    public ResponseEntity<APIResponse> save(@Valid @RequestBody Fornitore fornitore, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new APIResponse("Name can not be null"));
        }

        Fornitore saved = fornitoreRepo.save(fornitore);
        return ResponseEntity.ok(new APIResponse(saved));
    }


}
