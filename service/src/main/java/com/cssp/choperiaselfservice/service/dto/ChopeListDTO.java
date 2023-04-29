package com.cssp.choperiaselfservice.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ChopeListDTO extends ProdutoListDTO implements Serializable {

    private String etiquetaRFID;

    public ChopeListDTO(Long id, Double qtdeEstoque, String descricao, String etiquetaRFID) {
        super(id, qtdeEstoque, descricao);
        this.etiquetaRFID = etiquetaRFID;
    }

    public ChopeListDTO(Long id, Double qtdeEstoque, Double precoCompra, String descricao, String unidade,
                        Double pontoEncomenda, Double precoVenda, Double litro_chope, String etiquetaRFID) {
        super(id, qtdeEstoque, precoCompra, descricao, unidade, pontoEncomenda, precoVenda, litro_chope);
        this.etiquetaRFID = etiquetaRFID;
    }
}
