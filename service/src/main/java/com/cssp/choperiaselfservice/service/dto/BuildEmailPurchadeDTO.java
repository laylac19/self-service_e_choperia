package com.cssp.choperiaselfservice.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BuildEmailPurchadeDTO implements Serializable {
    private Long idCliente;
    private String nomeCliente;
    private String emailCliente;

    public BuildEmailPurchadeDTO(Long idCliente, String nomeCliente, String emailCliente) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
    }
}
