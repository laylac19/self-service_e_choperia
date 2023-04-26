package com.cssp.choperiaselfservice.service.dto;

import com.cssp.choperiaselfservice.domain.enums.FormaPagamento;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaixaDTO {

    private Long id;

    private Long idCliente;

    private Long idCompra;

    private Long idClientePrincipal;

    @NotNull
    private Double totalConta;

    private Double desconto;

    @NotNull
    private Double valorFinal;

    @NotNull
    private FormaPagamento formaPagamento;

    private Boolean ativo = true;

}
