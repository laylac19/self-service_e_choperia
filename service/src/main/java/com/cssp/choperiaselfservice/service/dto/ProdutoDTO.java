package com.cssp.choperiaselfservice.service.dto;

import com.cssp.choperiaselfservice.service.util.MensagemProdutoUtil;
import jakarta.validation.constraints.NotEmpty;
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
public class ProdutoDTO implements Serializable {

    private Long id;

    private String codigoBarras;

    private String etiquetaRFID;

    @NotNull(message = MensagemProdutoUtil.NULL_STOCK_QUANTITY)
    private Double qtdeEstoque;

    @NotNull(message = MensagemProdutoUtil.NULL_PURCHASE_PRICE)
    private Double precoCompra;

    @NotNull(message = MensagemProdutoUtil.DESCRIPTION_NULL_PRODUCT)
    @NotEmpty(message = MensagemProdutoUtil.DESCRIPTION_EMPTY_PRODUCT)
    private String descricao;

    @NotNull(message = MensagemProdutoUtil.NULL_UNIT)
    private String unidade;

    @NotNull(message = MensagemProdutoUtil.NULL_ORDER_POINT)
    private Double pontoEncomenda;

    private Double precoVenda;

    private Double litro_chope;

    private Boolean ativo = true;

    public ProdutoDTO(Long id, String descricao, String unidade, Double precoVenda) {
        this.id = id;
        this.descricao = descricao;
        this.unidade = unidade;
        this.precoVenda = precoVenda;
    }
}
