package com.develhope.co.biblioteca_prova.vendita;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class VenditaServiceTest {
    /*
    * VALIDAZIONE CARRELLO:
    * - salvaVendita
    * - sconto operatore maggiore del fidelity --> viene usato
    * - carrello vuoto
    * - ISBN duplicati nel carrello
    * - sconto operatore fuori range
    *
    * VALIDAZIONE UTENTE
    * - utente non trovato
    *
    * VALIDAZIONE LIBRO
    * - libro non trovato
    * */



}
