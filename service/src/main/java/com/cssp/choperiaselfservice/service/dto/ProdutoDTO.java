package com.cssp.choperiaselfservice.service.dto;

import com.cssp.choperiaselfservice.domain.enums.TipoProduto;
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

    @NotNull(message = MensagemProdutoUtil.NULL_BARCODE)
    @NotEmpty(message = MensagemProdutoUtil.EMPTY_BARCODE)
    private String codigoBarras;

    @NotNull(message = MensagemProdutoUtil.NULL_STOCK_QUANTITY)
    private Integer qtdeEstoque;

    @NotNull(message = MensagemProdutoUtil.NULL_PURCHASE_PRICE)
    private Double precoCompra;

    @NotNull(message = MensagemProdutoUtil.DESCRIPTION_NULL_PRODUCT)
    @NotEmpty(message = MensagemProdutoUtil.DESCRIPTION_EMPTY_PRODUCT)
    private String descricao;

    @NotNull(message = MensagemProdutoUtil.NULL_UNIT)
    private String unidade;

    @NotNull(message = MensagemProdutoUtil.NULL_ORDER_POINT)
    private Integer pontoEncomenda;

    @NotNull(message = MensagemProdutoUtil.NULL_SALE_PRICE)
    private Double precoVenda;

    @NotNull(message = MensagemProdutoUtil.NULL_PRODUCT_TYPE)
    @NotEmpty(message = MensagemProdutoUtil.PRODUCT_TYPE_EMPTY)
    private TipoProduto tipoProduto;

    private Boolean ativo = true;

}
