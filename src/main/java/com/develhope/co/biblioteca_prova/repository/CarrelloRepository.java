package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.Carrello;
import com.develhope.co.biblioteca_prova.models.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarrelloRepository extends JpaRepository<Carrello, Integer> {
//    @Query("SELECT SUM(l.prezzo)  FROM Carrello c \n" +
//            "JOIN c.libro l \n" +
//            "WHERE c.vendita.id = ?1 \n")
//    Double prezzoTotale(Integer vendita_id);//prezzoVendita?

    Page<Carrello> findByLibro(Libro libro, Pageable pageable);
}
