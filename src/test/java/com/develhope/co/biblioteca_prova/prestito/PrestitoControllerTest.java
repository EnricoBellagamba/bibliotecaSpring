package com.develhope.co.biblioteca_prova.prestito;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class PrestitoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUtenteNonLoggato() throws Exception{
        mockMvc.perform(get("/prestiti"))
                .andExpect(status().is(302));
    }

    @Test
    @WithMockUser(username = "test", roles = {"OPERATORE"})
    void testIsbnNonValido() throws Exception{

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


}
