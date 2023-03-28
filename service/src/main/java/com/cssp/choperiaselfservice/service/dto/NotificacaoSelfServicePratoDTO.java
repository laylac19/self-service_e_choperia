package com.cssp.choperiaselfservice.service.dto;

import com.cssp.choperiaselfservice.domain.enums.StatusPrato;
import com.cssp.choperiaselfservice.service.util.MensagemCozinhaUtil;
import jakarta.validation.constraints.NotEmpty;
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

    @NotNull(message = MensagemCozinhaUtil.NULL_DISH_DESCRIPTION)
    @NotEmpty(message = MensagemCozinhaUtil.EMPTY_DISH_DESCRIPTION)
    private String descricaoPrato;

    private StatusPrato statusPrato = StatusPrato.PENDENTE;

    private Boolean ativo = true;

}
