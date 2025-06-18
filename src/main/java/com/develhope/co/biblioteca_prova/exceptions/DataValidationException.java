package com.develhope.co.biblioteca_prova.exceptions;

public class DataValidationException extends RuntimeException{
    public DataValidationException(String messaggio){
        super(messaggio);
    }
}
