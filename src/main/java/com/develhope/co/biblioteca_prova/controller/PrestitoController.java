package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.dto.PaginationDTO;
import com.develhope.co.biblioteca_prova.repository.PrestitoRepository;
import com.develhope.co.biblioteca_prova.service.PrestitoService;
import com.develhope.co.biblioteca_prova.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrestitoController {
    @Autowired
    private PrestitoRepository prestitiRepo;
    @Autowired
    private PrestitoService prestitoService;

    public ResponseEntity<APIResponse> findAll(PaginationDTO pagination) {

        Pageable pageable = PaginationUtils.createPage(pagination);
        return ResponseEntity.ok().body(new APIResponse(prestitiRepo.findAll(pageable)));
    }
}
