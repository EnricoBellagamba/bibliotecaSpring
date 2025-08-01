package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.Acquisto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcquistoRepository extends JpaRepository<Acquisto, Integer> {

    Optional<Acquisto> findTopByOrderByIdDesc();
}
