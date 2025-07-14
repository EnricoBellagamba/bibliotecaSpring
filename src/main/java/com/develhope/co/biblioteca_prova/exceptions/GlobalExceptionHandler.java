//package com.develhope.co.biblioteca_prova.exceptions;
//
//import com.develhope.co.biblioteca_prova.dto.APIResponse;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    public ResponseEntity<APIResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
//        return ResponseEntity.badRequest()
//                .body(new APIResponse("Formato data non valido. Usa ISO 8601, es. 2025-07-13T10:00:00"));
//    }
//}
