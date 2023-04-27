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
public class EntradaProdutoDTO implements Serializable{
    @NotNull
    private Long id;
    @NotNull
    private Double qtdeEstoque;
}
