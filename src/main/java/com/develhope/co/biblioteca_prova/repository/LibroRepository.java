package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, String> {
}
