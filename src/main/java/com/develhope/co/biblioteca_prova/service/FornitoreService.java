package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.repository.FornitoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornitoreService {
    @Autowired
    private FornitoreRepository fornitoreRepo;
}
