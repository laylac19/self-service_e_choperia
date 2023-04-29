package com.cssp.choperiaselfservice.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoListDTO implements Serializable {

    private Long id;
    private Double qtdeEstoque;
    private Double precoCompra;
    private String descricao;
    private String unidade;
    private Double pontoEncomenda;
    private Double precoVenda;
    private Double litro_chope;

    public ProdutoListDTO(Long id, Double qtdeEstoque, String descricao) {
        this.id = id;
        this.qtdeEstoque = qtdeEstoque;
        this.descricao = descricao;
    }

    public ProdutoListDTO(Long id, Double qtdeEstoque, Double precoCompra, String descricao, String unidade,
                          Double pontoEncomenda, Double precoVenda, Double litro_chope) {
        this.id = id;
        this.qtdeEstoque = qtdeEstoque;
        this.precoCompra = precoCompra;
        this.descricao = descricao;
        this.unidade = unidade;
        this.pontoEncomenda = pontoEncomenda;
        this.precoVenda = precoVenda;
        this.litro_chope = litro_chope;
    }

    public ProdutoListDTO(Long id, Double qtdeEstoque, Double precoCompra, String descricao, String unidade,
                          Double pontoEncomenda, Double precoVenda) {
        this.id = id;
        this.qtdeEstoque = qtdeEstoque;
        this.precoCompra = precoCompra;
        this.descricao = descricao;
        this.unidade = unidade;
        this.pontoEncomenda = pontoEncomenda;
        this.precoVenda = precoVenda;
    }
}
