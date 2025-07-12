package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.Prestito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PrestitoRepository extends JpaRepository<Prestito, Integer> {

    @Query(
            value = "SELECT * FROM prestito WHERE data_prestito >= ?1 AND data_prestito < (?1 + INTERVAL 1 DAY)",
            countQuery = "SELECT COUNT(*) FROM prestito WHERE data_prestito >= ?1 AND data_prestito < (?1 + INTERVAL 1 DAY)",
            nativeQuery = true
    )
    Page<Prestito> findByDataPrestito(LocalDateTime dataPrestito, Pageable pageable);
}
