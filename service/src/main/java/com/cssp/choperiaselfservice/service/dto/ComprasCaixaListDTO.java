package com.cssp.choperiaselfservice.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ComprasCaixaListDTO implements Serializable {

    private Long idCompra;
    private String descricao;
    private Double qtde;
    private Double valor;

    public ComprasCaixaListDTO(Long idCompra, String descricao, Double qtde, Double valor) {
        this.idCompra = idCompra;
        this.descricao = descricao;
        this.qtde = qtde;
        this.valor = valor;
    }
}
