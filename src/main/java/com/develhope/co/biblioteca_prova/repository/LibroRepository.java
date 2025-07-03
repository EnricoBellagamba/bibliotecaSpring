package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.dto.LibroDTO;
import com.develhope.co.biblioteca_prova.models.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.List;


public interface LibroRepository extends JpaRepository<Libro, String> {

    Page<Libro> findByTitoloContains(String titolo, Pageable pageable);

    //    @NativeQuery("SELECT l.* FROM libro l\n" +
//            "JOIN acquisto a ON  a.libro_isbn = l.isbn\n" +
//            "JOIN ordine o ON  o.id  = a.ordine_id\n" +
//            "WHERE o.stato = 'CONSEGNATO'")


    // sottrarre i libri prestati
    @Query("SELECT l, CAST(SUM(a.numCopie) AS integer) FROM Libro l \n" +
            "JOIN l.acquisti a \n" +
            "JOIN a.ordine o \n" +
            "WHERE o.stato = 'CONSEGNATO' \n" +
            "GROUP BY l.isbn")
    Page<LibroDTO> findLibriDisponibili(Pageable pageable);

}
