package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.Prestito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestitoRepository extends JpaRepository<Prestito, Integer> {
}
