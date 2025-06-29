package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.repository.FornitoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fornitori")
public class FornitoreController {

    @Autowired
    FornitoreRepository fornitoreRepo;

    @GetMapping
    public ResponseEntity<APIResponse> getAll(){
        APIResponse ar = new APIResponse(fornitoreRepo.findAll());
        return ResponseEntity.ok(ar);
    }
}
