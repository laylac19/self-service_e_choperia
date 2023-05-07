package com.cssp.choperiaselfservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class RelatorioEnviarEmailDTO implements Serializable {
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private String mensagem;

}
