package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.PrestitoPerGiorno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PrestitoPerGiornoRepository extends JpaRepository<PrestitoPerGiorno, LocalDateTime> {
    List<PrestitoPerGiorno> findAllByGiornoBetween(LocalDateTime inizioData, LocalDateTime fineData);
}
