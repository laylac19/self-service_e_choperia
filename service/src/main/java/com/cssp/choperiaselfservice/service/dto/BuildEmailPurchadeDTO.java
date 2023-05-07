package com.cssp.choperiaselfservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuildEmailPurchadeDTO implements Serializable {
    private Long idCliente;
    private String nomeCliente;
    private String emailCliente;

    private Long idCompra;
    private LocalDate dataCompra;
    private Double valorCompra;
}
