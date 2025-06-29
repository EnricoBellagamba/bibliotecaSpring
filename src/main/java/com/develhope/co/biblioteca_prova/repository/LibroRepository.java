package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LibroRepository extends JpaRepository<Libro, String> {


    Page<Libro> findByTitoloContains(String titolo, Pageable pageable);

}
