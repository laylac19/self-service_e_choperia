package com.cssp.choperiaselfservice.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoRelatorioDTO implements Serializable {
    private Long id;
    private String codigoBarras;
    private String etiquetaRFID;
    private String nomeProduto;
    private Double qtdeEstoque;
    private Double precoCompra;
    private Double precoVenda;
    private String unidade;
    private Double pontoEncomenda;
    private Double totalConsumido;

    public ProdutoRelatorioDTO(Long id, String codigoBarras, String etiquetaRFID, String nomeProduto, Double qtdeEstoque,
                               String unidade) {
        this.id = id;
        this.codigoBarras = codigoBarras;
        this.etiquetaRFID = etiquetaRFID;
        this.nomeProduto = nomeProduto;
        this.qtdeEstoque = qtdeEstoque;
        this.unidade = unidade;
    }

    public ProdutoRelatorioDTO(Long id, String codigoBarras, String etiquetaRFID, String nomeProduto, Double qtdeEstoque,
                               String unidade, Double pontoEncomenda) {
        this.id = id;
        this.codigoBarras = codigoBarras;
        this.etiquetaRFID = etiquetaRFID;
        this.nomeProduto = nomeProduto;
        this.qtdeEstoque = qtdeEstoque;
        this.unidade = unidade;
        this.pontoEncomenda = pontoEncomenda;
    }

    public ProdutoRelatorioDTO(Long id, String etiquetaRFID, String nomeProduto, Double qtdeEstoque, Double precoCompra,
                               Double precoVenda) {
        this.id = id;
        this.etiquetaRFID = etiquetaRFID;
        this.nomeProduto = nomeProduto;
        this.qtdeEstoque = qtdeEstoque;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
    }
}
