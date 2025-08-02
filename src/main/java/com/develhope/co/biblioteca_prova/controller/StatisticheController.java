package com.develhope.co.biblioteca_prova.controller;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.dto.StatisticheDTO;
import com.develhope.co.biblioteca_prova.dto.VenditeConTotaleDTO;
import com.develhope.co.biblioteca_prova.models.PrestitoPerGiorno;
import com.develhope.co.biblioteca_prova.models.Vendita;
import com.develhope.co.biblioteca_prova.repository.PrestitoPerGiornoRepository;
import com.develhope.co.biblioteca_prova.repository.PrestitoRepository;
import com.develhope.co.biblioteca_prova.repository.VenditaRepository;
import com.develhope.co.biblioteca_prova.service.AcquistoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatisticheController {

    @Autowired
    private PrestitoRepository prestitoRepository;

    @Autowired
    private VenditaRepository venditaRepository;

    @Autowired
    private AcquistoService acquistoService;

    @Autowired
    private PrestitoPerGiornoRepository prestitoPerGiornoRepo;

    @GetMapping
    public ResponseEntity<APIResponse> statistiche(@RequestParam(required = false) LocalDateTime dataInizio,
                                                   @RequestParam(required = false) LocalDateTime dataFine) {

        if (dataFine == null) {
            dataFine = LocalDateTime.now();
        }

        if (dataInizio == null) {
            dataInizio = dataFine.minusDays(30);
        }

        int numeroPrestiti = prestitoRepository.countByDataPrestitoBetween(dataInizio, dataFine);
        int numeroVendite = venditaRepository.countByDataVenditaBetween(dataInizio, dataFine);

        List<Vendita> v = venditaRepository.findByDataVenditaBetween(dataInizio, dataFine);
        VenditeConTotaleDTO vct = new VenditeConTotaleDTO(v);
        double totaleVendite = vct.getTotale().doubleValue();
        BigDecimal bigTotaleVendite = BigDecimal.valueOf(totaleVendite).setScale(2, RoundingMode.DOWN);
        double totaleSpese = acquistoService.getTotaleSpese(dataInizio, dataFine);
        BigDecimal bigTotaleSpese = BigDecimal.valueOf(totaleSpese).setScale(2, RoundingMode.DOWN);
        double totaleFatturato = totaleVendite - totaleSpese;
        BigDecimal bigTotaleFatturato = BigDecimal.valueOf(totaleFatturato).setScale(2, RoundingMode.DOWN);

        Map<LocalDate, Integer> prestitiPerGiorno = new HashMap<>();
        List<PrestitoPerGiorno> prestitiPG = prestitoPerGiornoRepo.findAllByGiornoBetween(dataInizio, dataFine);

        for (PrestitoPerGiorno ppg : prestitiPG) {
            prestitiPerGiorno.put(ppg.getGiorno().toLocalDate(), ppg.getNumeroPrestiti());
        }

        StatisticheDTO statsDto = new StatisticheDTO(
                dataInizio,
                dataFine,
                numeroPrestiti,
                numeroVendite,
                bigTotaleFatturato,
                bigTotaleSpese,
                bigTotaleVendite,
                prestitiPerGiorno
        );

        return ResponseEntity.ok().body(new APIResponse(statsDto));
    }

}
