package com.cssp.choperiaselfservice.service.dto;

import com.cssp.choperiaselfservice.service.util.MensagemUsuarioUtil;
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
public class UsuarioDTO implements Serializable {

    private Long id;

    @NotNull(message = MensagemUsuarioUtil.NULL_USER)
    @NotEmpty(message = MensagemUsuarioUtil.EMPTY_USER)
    private String usuario;

    @NotNull(message = MensagemUsuarioUtil.NULL_NAME)
    @NotEmpty(message = MensagemUsuarioUtil.EMPTY_NAME)
    private String nome;

    private String senha;

    private Boolean ativo = true;

    @NotNull(message = MensagemUsuarioUtil.NULL_PROFILE)
    private Integer idPerfil;
}
