package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.repository.VenditaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenditaService {
    @Autowired
    private VenditaRepository venditaRepo;
}
