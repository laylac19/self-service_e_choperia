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
public class ClienteRelatorioDTO implements Serializable {

    private Long id;
    private String nome;
    private String numCartaoRFID;
    private String cpf;
    private Double totalComprado;
}
