package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.dto.PaginationDTO;
import com.develhope.co.biblioteca_prova.models.Carrello;
import com.develhope.co.biblioteca_prova.repository.CarrelloRepository;
import com.develhope.co.biblioteca_prova.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/carrello")
public class CarrelloController {
    @Autowired
    private CarrelloRepository carrelloRepo;

    @GetMapping
    public ResponseEntity<APIResponse> findAll(
            PaginationDTO pagination) {
        Pageable pageable = PaginationUtils.createPage(pagination);
        return ResponseEntity.ok(new APIResponse(carrelloRepo.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findById(@PathVariable Integer id){
        Optional<Carrello> carrelloOpt = carrelloRepo.findById(id);
        if(carrelloOpt.isEmpty()){
            return ResponseEntity.badRequest().body(new APIResponse("Carrello con id: "+id+" non trovato"));
        }
        return ResponseEntity.ok(new APIResponse(carrelloOpt.get()));
    }

    @PostMapping
    public ResponseEntity<APIResponse> save(@RequestBody Carrello carrello) {


        if(carrello.getId() == null){
            carrello.setPrezzoPerCopia(carrelloRepo.prezzoTotale(carrello.getVendita().getId()));
        }
        return ResponseEntity.ok(new APIResponse(carrelloRepo.save(carrello)));
    }



}
