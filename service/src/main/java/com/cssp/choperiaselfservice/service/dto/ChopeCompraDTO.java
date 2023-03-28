package com.cssp.choperiaselfservice.service.dto;

import com.cssp.choperiaselfservice.service.util.MensagemClienteCompraUtil;
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
public class ChopeCompraDTO extends ClienteCompraDTO implements Serializable {

    @NotNull(message = MensagemClienteCompraUtil.ID_CHOPE_NULL)
    private Long idChope;
}
