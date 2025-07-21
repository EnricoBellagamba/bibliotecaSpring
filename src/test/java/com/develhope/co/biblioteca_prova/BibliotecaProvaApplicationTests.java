package com.develhope.co.biblioteca_prova;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BibliotecaProvaApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void testAuthFailed() throws Exception {
        mockMvc.perform(get("/libri")).andExpect(status().is(302));
    }

    @Test
    @WithMockUser(username = "lucarosso")
    void testAuthSuccess() throws Exception {
        mockMvc.perform(get("/libri")).andExpect(status().is(200));
    }


}
