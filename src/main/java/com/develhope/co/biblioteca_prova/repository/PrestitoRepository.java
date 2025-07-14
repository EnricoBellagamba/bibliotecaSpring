package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.Prestito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PrestitoRepository extends JpaRepository<Prestito, Integer> {

    @Query(
            """
                    SELECT p FROM Prestito p
                    WHERE FUNCTION('date', p.dataPrestito) = ?1
                    """
    )
    Page<Prestito> findByDataPrestito(LocalDate dataPrestito, Pageable pageable);

}
