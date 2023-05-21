package com.cssp.choperiaselfservice.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BuildEmailPurchadeDTO implements Serializable {
    private Long idCliente;
    private String nomeCliente;
    private String emailCliente;

    private Long idCompra;
    private LocalDate dataCompra;
    private Double valorCompra;

    public BuildEmailPurchadeDTO(Long idCliente, String nomeCliente, String emailCliente, Long idCompra,
                                 LocalDate dataCompra, Double valorCompra) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
        this.idCompra = idCompra;
        this.dataCompra = dataCompra;
        this.valorCompra = valorCompra;
    }
}
