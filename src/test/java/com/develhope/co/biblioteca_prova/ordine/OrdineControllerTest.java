package com.develhope.co.biblioteca_prova.ordine;

import com.develhope.co.biblioteca_prova.models.Acquisto;
import com.develhope.co.biblioteca_prova.repository.AcquistoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrdineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AcquistoRepository acquistoRepo;

    @Test
    @WithMockUser(username = "test", roles = {"OPERATORE"})
    void create() throws Exception{
    Acquisto ultimoAcquisto = acquistoRepo.findTopByOrderByIdDesc().get();
        String ordineJson = """
                {
                    "tipo": "ACQUISTO",
                    "acquisti": [
                        {
                            "id":"""
                        +ultimoAcquisto.getId()+
                            """
                            ,
                            "numCopie": 5,
                            "prezzoPerCopia": 10.0,
                            "libro":{
                                "isbn": "978-210-8472-010",
                                "autori": [{"id": 10}]
                            }
                        }
                    ],
                    "fornitore": {"id": 3}
                }
                """;

        mockMvc.perform(post("/ordini")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ordineJson))
                .andDo(print())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.content.fornitore.nome").value("Giunti Editore"))
                .andExpect(status().isOk());
    }
}
