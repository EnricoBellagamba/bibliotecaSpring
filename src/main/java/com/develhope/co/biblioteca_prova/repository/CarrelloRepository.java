package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.Carrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarrelloRepository extends JpaRepository<Carrello, Integer> {
    @Query("SELECT CAST(SUM(l.prezzo) AS double) FROM Carrello c \n" +
            "JOIN c.libro l \n" +
            "WHERE c.vendita = ?1 \n")
    Double prezzoTotale(Integer vendita_id);
}
