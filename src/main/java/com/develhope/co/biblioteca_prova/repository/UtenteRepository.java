package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
}
