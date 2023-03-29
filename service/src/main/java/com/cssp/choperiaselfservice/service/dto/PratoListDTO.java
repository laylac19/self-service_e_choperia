package com.cssp.choperiaselfservice.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PratoListDTO implements Serializable {

    private Long id;

    @NotNull
    private String descricao;

    @NotNull
    private Double precoKg;

    private Boolean ativo = true;

    public PratoListDTO(Long id, String descricao, Double precoKg) {
        this.id = id;
        this.descricao = descricao;
        this.precoKg = precoKg;
    }
}
