package com.develhope.co.biblioteca_prova.acquisto;

import com.develhope.co.biblioteca_prova.models.Acquisto;
import com.develhope.co.biblioteca_prova.models.Fornitore;
import com.develhope.co.biblioteca_prova.models.Libro;
import com.develhope.co.biblioteca_prova.models.Ordine;
import com.develhope.co.biblioteca_prova.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static com.develhope.co.biblioteca_prova.enums.TipoOrdine.ACQUISTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AcquistoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AcquistoRepository acquistoRepo;

    @Autowired
    private LibroRepository libroRepo;

    @Autowired
    private OrdineRepository ordineRepo;

    @Autowired
    private FornitoreRepository fornitoreRepo;

    @Autowired
    private ObjectMapper objectMapper;

    private Acquisto a;
    private Libro l;
    private Ordine o;
    private Fornitore f;

    @BeforeAll
    void setup() {

        Fornitore fornitore = new Fornitore();
        fornitore.setNome("FornitoreTest");
        f = fornitoreRepo.save(fornitore);

        Ordine ordine = new Ordine();
        ordine.setDataOrdine(LocalDateTime.of(2025, 9, 15, 10, 0, 0));
        ordine.setTipo(ACQUISTO);
        ordine.setFornitore(f);
        o = ordineRepo.save(ordine);

        Libro libro = new Libro();
        libro.setIsbn("111-222-3333-445");
        libro.setTitolo("Titolo di prova");
        libro.setPrezzo(15.00);
        l = libroRepo.save(libro);

        Acquisto acquisto = new Acquisto();
        acquisto.setNumCopie(3);
        acquisto.setPrezzoPerCopia(15.00);
        acquisto.setLibro(l);
        acquisto.setOrdine(o);
        a = acquistoRepo.save(acquisto);
    }

    @Test
    void getAllAcquisti() throws Exception {
        mockMvc.perform(get("/acquisti"))
                .andExpect(status().isOk());
    }

    @Test
    void getAcquistoById() throws Exception {
        mockMvc.perform(get("/acquisti/" + a.getId()))
                .andExpect(status().isOk());
    }

//    @Test
//    void createAcquisto() throws Exception {
//        String acquistoJson = objectMapper.writeValueAsString(a);
//
//        mockMvc.perform(post("/acquisti")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(acquistoJson))
//                .andExpect(status().isOk());
//
//    }

}
