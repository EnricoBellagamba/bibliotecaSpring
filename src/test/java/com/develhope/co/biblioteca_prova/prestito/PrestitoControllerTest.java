package com.develhope.co.biblioteca_prova.prestito;

import com.develhope.co.biblioteca_prova.models.Libro;
import com.develhope.co.biblioteca_prova.models.Prestito;
import com.develhope.co.biblioteca_prova.models.Utente;
import com.develhope.co.biblioteca_prova.repository.LibroRepository;
import com.develhope.co.biblioteca_prova.repository.PrestitoRepository;
import com.develhope.co.biblioteca_prova.repository.UtenteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class PrestitoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PrestitoRepository prestitoRepo;


    @Autowired
    private UtenteRepository utenteRepo;

    @Autowired
    private LibroRepository libroRepo;

    /**
     * Verifica che una richesta per l'endpoint "/prestiti" protetto da autenticazione,
     * senza login effettuato (test senza @WithMockUser) ha come risposta un redirect
     *
     * @throws Exception
     */
    @Test
    void testUtenteNonLoggato() throws Exception {
        mockMvc.perform(get("/prestiti"))
                .andExpect(status().is(302));
    }

    /**
     * @throws Exception
     */
    @Test
    @WithMockUser(username = "test", roles = {"OPERATORE"})
    void testIsbnNonValido() throws Exception {

        String bodyJson = """
                {
                    "dataPrestito": "2025-07-01T09:00:00",
                    "dataRestituzione": "2025-01-04T15:30:00",
                    "utente": {
                                "id": 1
                
                            },
                    "libro": {
                        "isbn": "11111111"
                    }
                }
                """;

        mockMvc.perform(post("/prestiti")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("Libro con ISBN 11111111 non trova copie nel database")
                )
                .andExpect(jsonPath("$.success").value(false));
    }

    /**
     * test vincolo resttuzione 60 giorni
     *
     * @throws Exception
     */
    @Test
    @WithMockUser(username = "test", roles = {"OPERATORE"})
    void testPrestitoNonRestituitoOltre60Giorni() throws Exception {

        //prestito da testare
//        Optional<Libro> libroOpt = libroRepo.findById("978-210-8472-010");
//        Optional<Utente> utenteOpt = utenteRepo.findById(1);
        Optional<Prestito> prestitoOpt = prestitoRepo.findById(9);
        Prestito prestito = prestitoOpt.get();
//        prestito.setLibro(libroOpt.get());
//        prestito.setUtente(utenteOpt.get());
        String prestitoJson = objectMapper.writeValueAsString(prestito);

        mockMvc.perform(post("/prestiti")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(prestitoJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("L'utente ha un prestito attivo da più di 60 giorni")
                )
                .andExpect(jsonPath("$.success").value(false));
    }

    /**
     * tets vincolo 5 prestiti attivi
     */


    /**
     * Verifica che un utente con più di 5 prestiti attivi non possa creare un nuovo prestito.
     */
    @Test
    @WithMockUser(username = "test", roles = {"OPERATORE"})
    void testVincoloCinquePrestitiAttivi() throws Exception {
        // Prepara i dati

        Optional<Utente> opt = utenteRepo.findById(2) ;


        Optional<Libro> libroOpt = libroRepo.findById("978-539-9032-054");



        // Crea 6 prestiti attivi
        for (int i = 1; i <= 6; i++) {
            Prestito prestitoAttivo = new Prestito();
            prestitoAttivo.setUtente(opt.get());
            prestitoAttivo.setLibro(libroOpt.get());
            prestitoAttivo.setDataPrestito(LocalDateTime.of(2025, 7, 1, 9, 0));
            prestitoAttivo.setDataRestituzione(null); // Non restituito
            prestitoRepo.save(prestitoAttivo);
        }

        Prestito nuovoPrestito = new Prestito();
        nuovoPrestito.setUtente(opt.get());
        nuovoPrestito.setLibro(libroOpt.get());
        nuovoPrestito.setDataPrestito(LocalDateTime.of(2025, 7, 1, 9, 0));
        nuovoPrestito.setDataRestituzione(null);

        mockMvc.perform(post("/prestiti")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuovoPrestito)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("L'utente non può avere più di 5 prestiti attivi"))
                .andExpect(jsonPath("$.success").value(false));
    }
}



