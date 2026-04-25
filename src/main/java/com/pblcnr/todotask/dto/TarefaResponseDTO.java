package com.pblcnr.todotask.dto;

import java.time.LocalDateTime;

import com.pblcnr.todotask.model.Tarefa;
import com.pblcnr.todotask.model.enums.StatusTarefa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private StatusTarefa status;
    private String observacao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public static TarefaResponseDTO fromEntity(Tarefa tarefa) {
        return new TarefaResponseDTO(
                tarefa.getId(),
                tarefa.getNome(),
                tarefa.getDescricao(),
                tarefa.getStatus(),
                tarefa.getObservacao(),
                tarefa.getDataCriacao(),
                tarefa.getDataAtualizacao());
    }
}
