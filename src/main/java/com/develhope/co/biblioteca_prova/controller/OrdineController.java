package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.dto.PaginationDTO;
import com.develhope.co.biblioteca_prova.repository.OrdineRepository;
import com.develhope.co.biblioteca_prova.service.OrdineService;
import com.develhope.co.biblioteca_prova.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordini")
public class OrdineController {
    @Autowired
    private OrdineRepository ordiniRepo;
    @Autowired
    private OrdineService ordineService;

    @GetMapping("")
    public ResponseEntity<APIResponse> findAll(PaginationDTO pagination) {

        Pageable pageable = PaginationUtils.createPage(pagination);
        return ResponseEntity.ok().body(new APIResponse(ordiniRepo.findAll(pageable)));
    }

}
