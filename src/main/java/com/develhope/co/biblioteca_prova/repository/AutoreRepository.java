package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutoreRepository extends JpaRepository<Autore, Integer> {

    @Query("""
            SELECT a FROM Libro l JOIN l.autori a
            WHERE l.isbn = :isbn
            """)
    List<Autore> findByIsbn(String isbn);
}
