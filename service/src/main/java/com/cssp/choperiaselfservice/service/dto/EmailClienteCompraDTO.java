package com.cssp.choperiaselfservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class EmailClienteCompraDTO implements Serializable {
    private String nomeCliente;
    private String titulo;
    private String mensagem;
    private LocalDate dataCompra;
    private LocalDate dtInicioPeriodo;
    private LocalDate dtFimPeriodo;
}
