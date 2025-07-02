package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.dto.PaginationDTO;
import com.develhope.co.biblioteca_prova.models.Acquisto;
import com.develhope.co.biblioteca_prova.repository.AcquistoRepository;
import com.develhope.co.biblioteca_prova.repository.LibroRepository;
import com.develhope.co.biblioteca_prova.service.AcquistoService;
import com.develhope.co.biblioteca_prova.utils.PaginationUtils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acquisti")
public class AcquistoController {

    @Autowired
    private AcquistoRepository acquistoRepo;

    @Autowired
    private AcquistoService acquistoService;

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findById(@PathVariable Integer id) {
        Optional<Acquisto> opt = acquistoRepo.findById(id);
        if(opt.isEmpty()) {
            return ResponseEntity.status(404).body(new APIResponse("acquisto non trovato")) ;
        }
        return ResponseEntity.ok(new APIResponse(opt.get()));
    }

    @GetMapping
    public ResponseEntity<APIResponse> findAll(PaginationDTO pagination) {
    Pageable pageable = PaginationUtils.createPage(pagination);

       return ResponseEntity.ok(new APIResponse(acquistoRepo.findAll(pageable)));
    }
    
    @PostMapping
    public ResponseEntity<APIResponse> save(@RequestBody Acquisto acquisto){
        APIResponse response = acquistoService.salvaAcquisto(acquisto);
        return ResponseEntity.ok(new APIResponse(true,response.getStatus(),response.getContent()));

    }


}
