package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.Articolo;
import com.develhope.co.biblioteca_prova.models.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticoloRepository extends JpaRepository<Articolo, Integer> {
//    @Query("SELECT SUM(l.prezzo)  FROM Carrello c \n" +
//            "JOIN c.libro l \n" +
//            "WHERE c.vendita.id = ?1 \n")
//    Double prezzoTotale(Integer vendita_id);//prezzoVendita?

    Page<Articolo> findByLibro(Libro libro, Pageable pageable);
}
