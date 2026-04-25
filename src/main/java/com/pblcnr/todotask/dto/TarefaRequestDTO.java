package com.pblcnr.todotask.dto;

import com.pblcnr.todotask.model.Tarefa;
import com.pblcnr.todotask.model.enums.StatusTarefa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaRequestDTO {

    private String nome;
    private String descricao;
    private StatusTarefa status;
    private String observacao;

    public Tarefa toEntity() {
        return new Tarefa(null, this.nome, this.descricao, this.status, this.observacao, null, null);
    }

}
