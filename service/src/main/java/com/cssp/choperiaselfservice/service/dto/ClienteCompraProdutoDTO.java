package com.cssp.choperiaselfservice.service.dto;

import com.cssp.choperiaselfservice.service.util.MensagemClienteCompraUtil;
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
public class ClienteCompraProdutoDTO implements Serializable {

    private Long id;

    @NotNull(message = MensagemClienteCompraUtil.NULL_CUSTOMER)
    private Long idCliente;

    @NotNull
    private Long idProduto;

    @NotNull(message = MensagemClienteCompraUtil.PURCHASE_VALUE_NULL)
    @NotEmpty(message = MensagemClienteCompraUtil.PURCHASE_VALUE_EMPTY)
    private Double valorCompra;

    private Boolean ativo = true;

}
