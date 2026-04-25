package com.pblcnr.todotask.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pblcnr.todotask.dto.TarefaRequestDTO;
import com.pblcnr.todotask.dto.TarefaResponseDTO;
import com.pblcnr.todotask.service.TarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<TarefaResponseDTO> create(@RequestBody TarefaRequestDTO dto) {
        return ResponseEntity.status(201).body(TarefaResponseDTO.fromEntity(tarefaService.create(dto.toEntity())));
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> getAll() {
        List<TarefaResponseDTO> tarefas = tarefaService.getAll()
                .stream()
                .map(TarefaResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> getById(@PathVariable Long id) {
        return tarefaService.getById(id)
                .map(TarefaResponseDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> update(@PathVariable Long id, @RequestBody TarefaRequestDTO dto) {
        return tarefaService.update(id, dto.toEntity())
                .map(TarefaResponseDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            tarefaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}