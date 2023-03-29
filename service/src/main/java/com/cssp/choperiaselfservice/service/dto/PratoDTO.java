package com.cssp.choperiaselfservice.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PratoDTO implements Serializable {

    private Long id;

    @NotNull
    private String descricao;

    @NotNull
    private Double precoKg;

    private Boolean ativo = true;

}
