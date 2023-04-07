package com.cssp.choperiaselfservice.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PratoDTO implements Serializable {

    private Long id;

    @NotNull
    private String descricao;

    @NotNull
    private Double precoKg;

    private Boolean ativo = true;

    public PratoDTO(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
