package com.develhope.co.biblioteca_prova.vendita;

import com.develhope.co.biblioteca_prova.enums.StatoOrdine;
import com.develhope.co.biblioteca_prova.models.Libro;
import com.develhope.co.biblioteca_prova.models.Ordine;
import com.develhope.co.biblioteca_prova.models.Utente;
import com.develhope.co.biblioteca_prova.models.Vendita;
import com.develhope.co.biblioteca_prova.repository.LibroRepository;
import com.develhope.co.biblioteca_prova.repository.UtenteRepository;
import com.develhope.co.biblioteca_prova.repository.VenditaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static com.develhope.co.biblioteca_prova.enums.RuoloUtenti.CLIENTE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VenditaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VenditaRepository venditaRepo;

    @Autowired
    private UtenteRepository utenteRepo;

    @Autowired
    private LibroRepository libroRepo;

    private Vendita v;
    private Utente u;
    private Libro l;

    @BeforeAll
    void setup() {
        Utente utente = new Utente();
        utente.setNome("Mario");
        utente.setUsername("mrossi1");
        utente.setPassword("psw");
        utente.setRuolo(CLIENTE);
        u = utenteRepo.save(utente);

        Libro libro = new Libro();
        libro.setIsbn("111-222-3333-444");
        libro.setTitolo("Titolo di prova");
        libro.setPrezzo(15.00);
        l = libroRepo.save(libro);

        Vendita vendita = new Vendita();
        vendita.setUtente(u);
        vendita.setDataVendita(LocalDateTime.now());
        v = venditaRepo.save(vendita);
    }

    // GET /vendita - senza parametri
    @Test
    void getAllVendite() throws Exception {
        mockMvc.perform(get("/vendita"))
                .andExpect(status().isOk());
    }

    // GET /vendita - date valide
    @Test
    void getAllVendite_conDate() throws Exception {
        mockMvc.perform(get("/vendita")
                        .param("start", LocalDateTime.now().minusDays(10).toString())
                        .param("end", LocalDateTime.now().toString()))
                .andExpect(status().isOk());
    }

    // GET /vendita/{id} - ID valido
    @Test
    void getVenditaById() throws Exception {
        mockMvc.perform(get("/vendita/" + v.getId()))
                .andExpect(status().isOk());
    }

    // GET /vendita/{id} - ID non valido
    @Test
    void getVenditaById_nonValido() throws Exception {
        mockMvc.perform(get("/vendita/-1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value("Vendita non trovata"));
    }

    // POST /vendita - corpo valido
    @Test
    void createVendita() throws Exception {

        String bodyJson = String.format("""
                {
                  "dataVendita": "2025-07-29T09:30:00",
                  "utente": {
                    "id": %d
                  },
                  "carrello": [
                    {
                      "numeroCopie": 1,
                      "prezzoPerCopia": 12.14,
                      "libro": { "isbn": "%s" }
                    }
                  ]
                }
                """, u.getId(), l.getIsbn());

        mockMvc.perform(post("/vendita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson))
                .andExpect(status().isOk());
    }

    // POST /vendita - corpo non valido
    @Test
    void createVendita_carrelloVuoto() throws Exception {
        String bodyJson = """
                {
                    "utente": { "id": u },
                    "carrello": []
                }
                """;

        mockMvc.perform(post("/vendita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson))
                .andExpect(status().isBadRequest());
    }

    // POST /vendita - BindingResult errori
    @Test
    void creaVendita_campoMancante() throws Exception {
        String bodyJson = """
                {
                    "utente": {},
                    "carrello": [
                        {
                            "numeroCopie": 1,
                            "libro": { %s }
                        }
                    ]
                }
                """;

        mockMvc.perform(post("/vendita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson))
                .andExpect(status().isBadRequest());
    }

    // POST /vendita - sconto fuori range
    @Test
    void creaVendita_scontoInvalido() throws Exception {
        String bodyJson = String.format("""
                {
                    "utente": { "id": %d },
                    "carrello": [
                        {
                            "numeroCopie": 1,
                            "libro": { %s }
                        }
                    ]
                }
                """, u.getId(), l.getIsbn());

        mockMvc.perform(post("/vendita")
                        .param("sconto", "1.5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson))
                .andExpect(status().isBadRequest());
    }
}
