package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.dto.PaginationDTO;
import com.develhope.co.biblioteca_prova.repository.CarrelloRepository;
import com.develhope.co.biblioteca_prova.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrello")
public class CarrelloController {
    @Autowired
    private CarrelloRepository carrelloRepo;

    @GetMapping
    public ResponseEntity<APIResponse> findAll(
            PaginationDTO pagination,
            @RequestParam Integer venditaId
    ) {
        Pageable pageable = PaginationUtils.createPage(pagination);
        //return ResponseEntity.ok(new APIResponse(carrelloRepo.findAll(pageable)));
        return ResponseEntity.ok(new APIResponse(carrelloRepo.prezzoTotale(venditaId)));
    }

}
