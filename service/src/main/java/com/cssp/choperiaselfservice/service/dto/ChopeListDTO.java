package com.cssp.choperiaselfservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ChopeListDTO extends ProdutoListDTO implements Serializable {

    private String etiquetaRFID;

    public ChopeListDTO(Long id, Double qtdeEstoque, Double precoCompra, String descricao, String unidade,
                        Double pontoEncomenda, Double precoVenda, String etiquetaRFID) {
        super(id, qtdeEstoque, precoCompra, descricao, unidade, pontoEncomenda, precoVenda);
        this.etiquetaRFID = etiquetaRFID;
    }

    public ChopeListDTO(Long id, Double qtdeEstoque, String descricao, String etiquetaRFID) {
        super(id, qtdeEstoque, descricao);
        this.etiquetaRFID = etiquetaRFID;
    }
}
