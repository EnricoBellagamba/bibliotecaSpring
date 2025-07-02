package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.dto.APIResponse;
import com.develhope.co.biblioteca_prova.models.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface OrdineRepository extends JpaRepository<Ordine, Integer> {

    List<Ordine> findByStato(Enum stato);
}
