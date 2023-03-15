package com.cssp.choperiaselfservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioListDTO implements Serializable {

    private Long id;
    private String usuario;
    private String nome;
    private Long idPerfil;
    private String descPerfil;
    private Boolean ativo;
}
