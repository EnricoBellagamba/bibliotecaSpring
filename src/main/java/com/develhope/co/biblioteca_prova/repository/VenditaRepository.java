package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.Vendita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface VenditaRepository extends JpaRepository<Vendita, Integer> {
    Page<Vendita> findByDataVenditaBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
}
