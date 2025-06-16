package com.develhope.co.biblioteca_prova.repository;

import com.develhope.co.biblioteca_prova.models.Libri;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibriRepository extends JpaRepository<Libri, String> {


}
