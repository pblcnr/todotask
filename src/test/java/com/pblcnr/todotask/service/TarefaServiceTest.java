package com.pblcnr.todotask.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pblcnr.todotask.model.Tarefa;
import com.pblcnr.todotask.model.enums.StatusTarefa;
import com.pblcnr.todotask.repository.TarefaRepository;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    @Test
    void shouldCreateTarefaCorrectly() {
        Tarefa tarefa = new Tarefa(null, "Tarefa 1", "Descricao", StatusTarefa.PENDENTE, "Obs", null, null);

        when(tarefaRepository.save(any())).thenReturn(tarefa);

        Tarefa result = tarefaService.create(tarefa);

        assertNotNull(result);
        assertEquals(tarefa, result);

    }

    @Test
    void shouldGetAllTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        tarefas.add(new Tarefa(null, "Tarefa 1", "Descricao", StatusTarefa.PENDENTE, "Obs", null, null));
        tarefas.add(new Tarefa(null, "Tarefa 2", "Descricao", StatusTarefa.PENDENTE, "Obs", null, null));

        when(tarefaRepository.findAll()).thenReturn(tarefas);

        List<Tarefa> result = tarefaService.getAll();

        assertNotNull(result);
        assertEquals(tarefas, result);
    }

}
