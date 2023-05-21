package com.cssp.choperiaselfservice.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClienteListDTO implements Serializable {

    private Long id;
    private String numCartaoRFID;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public ClienteListDTO(Long id, String numCartaoRFID, String nome, String cpf) {
        this.id = id;
        this.numCartaoRFID = numCartaoRFID;
        this.nome = nome;
        this.cpf = cpf;
    }

    public ClienteListDTO(Long id, String numCartaoRFID, String nome, String cpf, String telefone, String email) {
        this.id = id;
        this.numCartaoRFID = numCartaoRFID;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }
}
