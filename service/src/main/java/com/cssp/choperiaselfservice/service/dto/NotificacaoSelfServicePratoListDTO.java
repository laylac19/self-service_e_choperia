package com.cssp.choperiaselfservice.service.dto;

import com.cssp.choperiaselfservice.domain.enums.StatusPrato;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class NotificacaoSelfServicePratoListDTO implements Serializable {

    private Long id;
    private String prato;
    private StatusPrato statusPrato;

    public NotificacaoSelfServicePratoListDTO(Long id, String prato, StatusPrato statusPrato) {
        this.id = id;
        this.prato = prato;
        this.statusPrato = statusPrato;
    }
}
