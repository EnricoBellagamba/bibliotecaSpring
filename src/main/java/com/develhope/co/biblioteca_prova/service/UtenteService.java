package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepo;
}
