package com.develhope.co.biblioteca_prova.dto;

import com.develhope.co.biblioteca_prova.models.Libro;

public class LibroConCopieDTO {
    private Libro libro;
    private Integer numCopie;

    public LibroConCopieDTO(Libro libro, Integer numCopie) {
        this.libro = libro;
        this.numCopie = numCopie;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Integer getNumCopie() {
        return numCopie;
    }

    public void setNumCopie(Integer numCopie) {
        this.numCopie = numCopie;
    }
}
