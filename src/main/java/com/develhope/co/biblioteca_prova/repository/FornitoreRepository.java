package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.Fornitore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornitoreRepository extends JpaRepository<Fornitore, Integer> {

    public Page<Fornitore> findByNomeContains(String nome, Pageable pageable);
}
