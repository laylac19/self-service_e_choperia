package com.cssp.choperiaselfservice.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ClienteSearchDTO implements Serializable {

    private Long id;
    private String numCartaoRFID;
    private String nome;

    public ClienteSearchDTO(Long id, String numCartaoRFID, String nome) {
        this.id = id;
        this.numCartaoRFID = numCartaoRFID;
        this.nome = nome;
    }
}
