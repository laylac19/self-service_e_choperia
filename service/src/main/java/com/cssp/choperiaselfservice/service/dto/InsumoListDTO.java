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
public class InsumoListDTO extends ProdutoListDTO implements Serializable {

    private String codigoBarras;

    public InsumoListDTO(Long id, Double qtdeEstoque, Double precoCompra, String descricao, String unidade,
                         Double pontoEncomenda, Double precoVenda, String codigoBarras) {
        super(id, qtdeEstoque, precoCompra, descricao, unidade, pontoEncomenda, precoVenda);
        this.codigoBarras = codigoBarras;
    }
}
