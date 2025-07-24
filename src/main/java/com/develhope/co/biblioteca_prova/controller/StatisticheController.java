package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/stats")
public class StatisticheController {

    @GetMapping
    public ResponseEntity<APIResponse> statistiche(@RequestParam(required = false) LocalDate dataInizio,
                                                   @RequestParam(required = false) LocalDate dataFine){

        if (dataFine == null){
            dataFine = LocalDate.now();
        }

        if (dataInizio == null){
            dataInizio = dataFine.minusDays(30);
        }
        //return statistiche dto
        //settare le vendite, prestiti in dto
        return null;
    }

}
