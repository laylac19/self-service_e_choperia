package com.cssp.choperiaselfservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoListDTO implements Serializable {

    private Long id;
    private Double qtdeEstoque;
    private Double precoCompra;
    private String descricao;
    private String unidade;
    private Double pontoEncomenda;
    private Double precoVenda;

    public ProdutoListDTO(Long id, Double qtdeEstoque, String descricao) {
        this.id = id;
        this.qtdeEstoque = qtdeEstoque;
        this.descricao = descricao;
    }
}
