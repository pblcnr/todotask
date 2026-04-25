package com.pblcnr.todotask.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pblcnr.todotask.model.Tarefa;
import com.pblcnr.todotask.repository.TarefaRepository;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa create(Tarefa tarefa) {
        return this.tarefaRepository.save(tarefa);
    }

    public List<Tarefa> getAll() {
        return this.tarefaRepository.findAll();
    }

    public Optional<Tarefa> getById(Long id) {
        return this.tarefaRepository.findById(id);
    }

    public Optional<Tarefa> update(Long id, Tarefa tarefa) {
        return this.tarefaRepository.findById(id).map(t -> {
            t.setId(id);
            return this.tarefaRepository.save(t);
        });
    }

    public void delete(Long id) {
        this.tarefaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Tarefa não encontrada com o ID: " + id));
        this.tarefaRepository.deleteById(id);
    }
}
