package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.dto.PaginationDTO;
import com.develhope.co.biblioteca_prova.models.Acquisto;
import com.develhope.co.biblioteca_prova.repository.AcquistoRepository;
import com.develhope.co.biblioteca_prova.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acquisti")
public class AcquistoController {

    @Autowired
    private AcquistoRepository acquistoRepository ;

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findById(@PathVariable Integer id) {
        Optional<Acquisto> opt = acquistoRepository.findById(id);
        if(opt.isEmpty()) {
            return ResponseEntity.status(404).body(new APIResponse("acquisto non trovato")) ;
        }
        return ResponseEntity.ok(new APIResponse(opt.get()));
    }

    @GetMapping
    public ResponseEntity<APIResponse> findAll(PaginationDTO pagination) {
    Pageable pageable = PaginationUtils.createPage(pagination);

       return ResponseEntity.ok(new APIResponse(acquistoRepository.findAll(pageable)));
    }


}
