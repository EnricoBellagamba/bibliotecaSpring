package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.LibroConCopie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroConCopieRepository extends JpaRepository<LibroConCopie, String> {

    public Page<LibroConCopie> findByCopieDisponibiliLessThanEqual(Integer numMinCopie, Pageable pageable);
}
