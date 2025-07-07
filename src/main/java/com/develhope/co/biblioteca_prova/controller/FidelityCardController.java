package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.models.FidelityCard;
import com.develhope.co.biblioteca_prova.models.Utente;
import com.develhope.co.biblioteca_prova.repository.FidelityCardRepository;
import com.develhope.co.biblioteca_prova.repository.UtenteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/fidelitycard")
public class FidelityCardController {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private FidelityCardRepository fidelityCardRepository;

    @PostMapping("/assegna/{idUtente}")
    public ResponseEntity<APIResponse> assegnaFidelityCard(@PathVariable("idUtente") Integer idUtente,
                                                           @Valid @RequestBody FidelityCard fidelityCard,
                                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            APIResponse apiResponse = new APIResponse(bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(apiResponse);
        }

        Optional<Utente> optionalUtente = utenteRepository.findById(idUtente);

        if (optionalUtente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

//        if (fidelityCard.getId() == null) {
//
//        } else {
//            Optional<FidelityCard> optionalFidelity = fidelityCardRepository.findById(fidelityCard.getId());
//        }

        Utente utente = optionalUtente.get();

        if (utente.getFidelityCard() == null) {
            fidelityCard.setUtente(utente);
            return ResponseEntity.ok().body(new APIResponse(fidelityCardRepository.save(fidelityCard)));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}
