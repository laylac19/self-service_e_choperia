package com.cssp.choperiaselfservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ComprasCaixaListDTO implements Serializable {

    private Long idCompra;
    private String descricao;
    private Double qtde;
    private Double valor;
}
