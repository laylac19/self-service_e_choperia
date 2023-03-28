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
public class InsumoDTO extends ProdutoDTO implements Serializable {

    @NotNull(message = MensagemProdutoUtil.NULL_BARCODE)
    @NotEmpty(message = MensagemProdutoUtil.EMPTY_BARCODE)
    private String codigoBarras;

}
