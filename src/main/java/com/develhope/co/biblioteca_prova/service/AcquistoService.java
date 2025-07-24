package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.repository.AcquistoRepository;
import com.develhope.co.biblioteca_prova.repository.LibroConCopieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AcquistoService {

    @Autowired
    private AcquistoRepository acquistoRepo;
    @Autowired
    private LibroConCopieRepository libroConCopieRepository;






}
