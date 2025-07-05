package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.dto.PaginationDTO;
import com.develhope.co.biblioteca_prova.enums.StatoOrdine;
import com.develhope.co.biblioteca_prova.exceptions.DataValidationException;
import com.develhope.co.biblioteca_prova.models.Ordine;
import com.develhope.co.biblioteca_prova.repository.OrdineRepository;
import com.develhope.co.biblioteca_prova.service.OrdineService;
import com.develhope.co.biblioteca_prova.utils.PaginationUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordini")
public class OrdineController {
    @Autowired
    private OrdineRepository ordiniRepo;
    @Autowired
    private OrdineService ordineService;

    @GetMapping("")
    public ResponseEntity<APIResponse> findAll(PaginationDTO pagination,
                                               @RequestParam(required = false) StatoOrdine statoOrdine) {
        if (statoOrdine == null) {
            Pageable pageable = PaginationUtils.createPage(pagination);
            return ResponseEntity.ok().body(new APIResponse(ordiniRepo.findAll(pageable)));
        }else {
            List<Ordine> ordineList = ordiniRepo.findByStato(statoOrdine);
            return ResponseEntity.ok().body(new APIResponse(ordineList));
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findById(@PathVariable("id") Integer id) {
        Optional<Ordine> o = ordiniRepo.findById(id);
        if (o.isPresent()) {
            return ResponseEntity.ok().body(new APIResponse(o.get()));
        }
        return ResponseEntity.status(404).body(new APIResponse("Ordine non trovato"));
    }
//    @GetMapping("/status/{stato}")
//    public ResponseEntity<APIResponse> findByStatus(@PathVariable("stato") StatoOrdine stato) {
//
//
//
//
//
//    }

    @PostMapping
    public ResponseEntity<APIResponse> save(@Valid @RequestBody Ordine ordine, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            APIResponse apiResponse = new APIResponse(bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(apiResponse);
        }
        try {
            if(ordine.getId() == null ) {
                ordine.setStato(StatoOrdine.IN_ATTESA);
            }
                //service con controllo se ordineStato è null
            return ResponseEntity.ok().body(new APIResponse(ordiniRepo.save(ordine)));
        } catch (DataValidationException | DataIntegrityViolationException e) {

            return ResponseEntity.badRequest().body(new APIResponse(e.getMessage()));
        }
    }


}
