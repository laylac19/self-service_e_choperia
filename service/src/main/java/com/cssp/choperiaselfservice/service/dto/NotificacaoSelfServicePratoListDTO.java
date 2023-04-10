package com.cssp.choperiaselfservice.service.dto;

import com.cssp.choperiaselfservice.domain.enums.StatusPrato;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoSelfServicePratoListDTO implements Serializable {

    private Long id;
    private Long idPrato;
    private String prato;
    private StatusPrato statusPrato;
}
