package com.cssp.choperiaselfservice.service.dto;

import com.cssp.choperiaselfservice.domain.enums.TipoProduto;
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
    private String codigoBarras;
    private Integer qtdeEstoque;
    private Double precoCompra;
    private String descricao;
    private String unidade;
    private Integer pontoEncomenda;
    private Double precoVenda;
    private TipoProduto tipoProduto;
}
