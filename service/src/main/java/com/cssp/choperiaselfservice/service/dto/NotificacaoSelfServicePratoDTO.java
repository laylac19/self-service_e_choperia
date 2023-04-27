package com.cssp.choperiaselfservice.service.dto;

import com.cssp.choperiaselfservice.domain.enums.StatusPrato;
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
public class NotificacaoSelfServicePratoDTO implements Serializable {

    private Long id;

    @NotNull
    private String descricaoPrato;

    private StatusPrato statusPrato = StatusPrato.PENDENTE;
    private Boolean ativo = true;

}
