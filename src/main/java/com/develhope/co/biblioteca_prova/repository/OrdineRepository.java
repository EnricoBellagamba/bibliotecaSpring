package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.enums.StatoOrdine;
import com.develhope.co.biblioteca_prova.models.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdineRepository extends JpaRepository<Ordine, Integer> {

    List<Ordine> findByStato(StatoOrdine stato);
}
