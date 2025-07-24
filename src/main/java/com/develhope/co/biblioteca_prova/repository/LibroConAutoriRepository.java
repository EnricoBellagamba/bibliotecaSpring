package com.develhope.co.biblioteca_prova.repository;


import com.develhope.co.biblioteca_prova.models.LibroConAutori;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroConAutoriRepository extends JpaRepository<LibroConAutori, String> {

    @Query("""
            SELECT lca FROM LibroConAutori lca
           
            WHERE
            (:titolo IS NULL OR lca.titolo LIKE CONCAT('%', :titolo, '%'))
            AND
            (:autore IS NULL
            OR lca.autorePrincipale LIKE CONCAT('%', :autore, '%')
            )
            """)
    Page<LibroConAutori> findByTitoloAndAutore(String titolo, String autore, Pageable pageable);
}
