package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.dto.PaginationDTO;
import com.develhope.co.biblioteca_prova.dto.VenditeConTotaleDTO;
import com.develhope.co.biblioteca_prova.exceptions.DataValidationException;
import com.develhope.co.biblioteca_prova.models.Vendita;
import com.develhope.co.biblioteca_prova.repository.VenditaRepository;
import com.develhope.co.biblioteca_prova.service.VenditaService;
import com.develhope.co.biblioteca_prova.utils.PaginationUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendita")
public class VenditaController {

    @Autowired
    private VenditaRepository venditaRepo;

    @Autowired
    private VenditaService venditaService;

    @GetMapping
    public ResponseEntity<APIResponse> findAll(
            @RequestParam(required = false) LocalDateTime start,
            @RequestParam(required = false) LocalDateTime end,
            PaginationDTO pagination) {
        Pageable pageable = PaginationUtils.createPage(pagination);

        if (start == null) {
            start = LocalDateTime.of(0, 1, 1, 0, 0);
        }
        if (end == null) {
            end = LocalDateTime.now();
        }

        return ResponseEntity.ok(
                new APIResponse(venditaRepo.findByDataVenditaBetween(start, end, pageable))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findById(
            @PathVariable("id") Integer id
    ) {
        Optional<Vendita> opt = venditaRepo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(404).body(new APIResponse("Vendita non trovata"));
        }
        return ResponseEntity.ok(new APIResponse(opt.get()));
    }

    @PostMapping
    public ResponseEntity<APIResponse> save(
            @Valid @RequestBody Vendita v,
            BindingResult br,
            @RequestParam(name = "sconto", required = false) Double scontoOperatore) {

        if (br.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(new APIResponse(br.getAllErrors()));
        }
       try {
           Vendita venditaSalvata = venditaService.salvaVendita(v,scontoOperatore);
           return ResponseEntity.ok(new APIResponse(venditaSalvata));
       }catch (DataValidationException | DataIntegrityViolationException e) {
           return ResponseEntity.badRequest().body(new APIResponse(e.getMessage()));
       }
    }
    @GetMapping("/statistiche")
    public VenditeConTotaleDTO findMeseCorrente(){
        return new VenditeConTotaleDTO(venditaService.findVenditeMeseCorrente()) ;
    }

}
