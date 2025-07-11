package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.dto.PaginationDTO;
import com.develhope.co.biblioteca_prova.models.Carrello;
import com.develhope.co.biblioteca_prova.models.Libro;
import com.develhope.co.biblioteca_prova.models.Utente;
import com.develhope.co.biblioteca_prova.models.Vendita;
import com.develhope.co.biblioteca_prova.repository.CarrelloRepository;
import com.develhope.co.biblioteca_prova.repository.LibroRepository;
import com.develhope.co.biblioteca_prova.repository.UtenteRepository;
import com.develhope.co.biblioteca_prova.repository.VenditaRepository;
import com.develhope.co.biblioteca_prova.service.FidelityCardService;
import com.develhope.co.biblioteca_prova.utils.PaginationUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/vendita")
public class VenditaController {
    @Autowired
    private VenditaRepository venditaRepo;

    @Autowired
    private CarrelloRepository carrelloRepository;

    @Autowired
    private LibroRepository libroRepo;

    @Autowired
    private FidelityCardService fidelityCardService;

    @Autowired
    private UtenteRepository utenteRepository;

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
        if (v.getCarrello() == null || v.getCarrello().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new APIResponse("Il carrello non puà essere vuoto"));
        }
        Integer utenteId = v.getUtente().getId();
        if (utenteId == null) {
            return ResponseEntity.badRequest()
                    .body(new APIResponse("L'utente non esiste"));
        }
        Optional<Utente> optionalUtente = utenteRepository.findById(utenteId);
        if (optionalUtente.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new APIResponse("L'utente è vuoto"));
        }
        v.setUtente(optionalUtente.get());
        for (Carrello c : v.getCarrello()) {
            Optional<Libro> opt = libroRepo.findById(c.getLibro().getIsbn());
            if (opt.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new APIResponse("Libro non presente nel db"));
            }else{
                c.setLibro(opt.get());
            }
        }

        Vendita vendita = venditaRepo.save(v);
        double sconto = fidelityCardService.calcolaSconto(vendita);
        if(scontoOperatore != null ){//controllo scontoOperatore tra 0 e 1
            sconto = scontoOperatore;
        }

        for (Carrello c : v.getCarrello()) {
            c.setVendita(vendita);
            double prezzoListino = c.getLibro().getPrezzo();
            double prezzoScontato = prezzoListino * (1 - sconto);
            System.out.println("prezzoscontato:" + prezzoScontato);
            System.out.println("prezzolistino:" + prezzoListino);
            System.out.println("sconto:" + sconto);
            c.setPrezzoVendita(prezzoScontato);
            carrelloRepository.save(c);
        }
        return ResponseEntity.ok(new APIResponse(vendita));
    }

}
