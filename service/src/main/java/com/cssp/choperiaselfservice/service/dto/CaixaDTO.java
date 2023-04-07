package com.cssp.choperiaselfservice.service.dto;

import com.cssp.choperiaselfservice.domain.Cliente;
import com.cssp.choperiaselfservice.domain.ClienteCompra;
import com.cssp.choperiaselfservice.domain.enums.FormaPagamento;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaixaDTO {

    private Long id;

    @NotNull
    private Long idRegistroClienteVenda;

    private Set<Cliente> clientes = new LinkedHashSet<>();

    private Set<ClienteCompra> clienteCompras = new LinkedHashSet<>();

    @NotNull
    private Double totalConta;

    @Column(name = "desconto")
    private Double desconto;

    @NotNull
    private Double valorFinal;

    @NotNull
    private FormaPagamento formaPagamento;

    private Boolean ativo = true;

}
