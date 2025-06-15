package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtentiRepository extends JpaRepository<Utenti, Integer> {
}
