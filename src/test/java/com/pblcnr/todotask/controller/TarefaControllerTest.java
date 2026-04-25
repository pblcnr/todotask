package com.pblcnr.todotask.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.pblcnr.todotask.repository.TarefaRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TarefaRepository tarefaRepository;

    @BeforeEach
    void setUp() {
        tarefaRepository.deleteAll();
    }

    @Test
    void shouldCreateTarefa() throws Exception {
        String json = """
                {
                    "nome": "Tarefa Teste",
                    "descricao": "Descricao",
                    "status": "PENDENTE",
                    "observacao": "Obs"
                }
                """;

        mockMvc.perform(post("/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Tarefa Teste"));
    }

    @Test
    void shouldReturnAllTarefas() throws Exception {
        String json = """
                {
                    "nome": "Tarefa Lista",
                    "descricao": "Descricao",
                    "status": "PENDENTE",
                    "observacao": "Obs"
                }
                """;

        mockMvc.perform(post("/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        mockMvc.perform(get("/tarefas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Tarefa Lista"));
    }
}