package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.repository.PrestitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestitoService {
    @Autowired
    private PrestitoRepository prestitoRepo;
}
