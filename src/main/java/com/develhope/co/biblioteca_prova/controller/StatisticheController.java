package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.dto.VenditeConTotaleDTO;
import com.develhope.co.biblioteca_prova.repository.PrestitoRepository;
import com.develhope.co.biblioteca_prova.repository.VenditaRepository;
import com.develhope.co.biblioteca_prova.service.AcquistoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/stats")
public class StatisticheController {

    @Autowired
    private PrestitoRepository prestitoRepository;

    @Autowired
    private VenditaRepository venditaRepository;

    @Autowired
    private VenditeConTotaleDTO venditeConTotaleDTO;

    @Autowired
    private AcquistoService acquistoService;

    @GetMapping
    public ResponseEntity<APIResponse> statistiche(@RequestParam(required = false) LocalDateTime dataInizio,
                                                   @RequestParam(required = false) LocalDateTime dataFine){

        if (dataFine == null){
            dataFine = LocalDateTime.now();
        }

        if (dataInizio == null){
            dataInizio = dataFine.minusDays(30);
        }

        int numeroPrestiti = prestitoRepository.countByDataPrestitoBetween(dataInizio, dataFine);
        int numeroVendite = venditaRepository.countByDataVenditaBetween(dataInizio, dataFine);

        double totaleFatturato = venditeConTotaleDTO.getTotale();
        double totaleSpese = acquistoService.getTotaleSpese(dataInizio, dataFine);

        //return statistiche dto
        //settare le vendite, prestiti in dto
        return null;
    }

}
