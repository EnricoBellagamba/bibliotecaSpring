package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.dto.PaginationDTO;
import com.develhope.co.biblioteca_prova.models.Articolo;
import com.develhope.co.biblioteca_prova.repository.ArticoloRepository;
import com.develhope.co.biblioteca_prova.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/articolo")
public class ArticoloController {
    @Autowired
    private ArticoloRepository articoloRepo;

    @GetMapping
    public ResponseEntity<APIResponse> findAll(
            PaginationDTO pagination) {
        Pageable pageable = PaginationUtils.createPage(pagination);
        return ResponseEntity.ok(new APIResponse(articoloRepo.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findById(@PathVariable Integer id){
        Optional<Articolo> articoloOpt = articoloRepo.findById(id);
        if(articoloOpt.isEmpty()){
            return ResponseEntity.badRequest().body(new APIResponse("Articolo con id: "+id+" non trovato"));
        }
        return ResponseEntity.ok(new APIResponse(articoloOpt.get()));
    }





}
