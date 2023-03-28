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
public class ClienteCompraListDTO implements Serializable {

    private Long id;
    private Long idCliente;
    private String nomeCliente;
    private String cartaoCliente;
    private Double valorCompra;
}
