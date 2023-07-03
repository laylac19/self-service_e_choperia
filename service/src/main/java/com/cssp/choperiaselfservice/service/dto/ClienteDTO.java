package com.cssp.choperiaselfservice.service.dto;

import com.cssp.choperiaselfservice.service.util.MensagemClienteUtil;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO implements Serializable {

    private Long id;

    private String numCartaoRFID;

    @NotNull(message = MensagemClienteUtil.NULL_CUSTOMER_NAME)
    @NotEmpty(message = MensagemClienteUtil.EMPTY_CUSTOMER_NAME)
    private String nome;

    @NotNull(message = MensagemClienteUtil.PHONE_NULL)
    @NotEmpty(message = MensagemClienteUtil.PHONE_EMPTY)
    private String telefone;

    @NotNull
    private String email;

    @NotNull(message = MensagemClienteUtil.CPF_NULL)
    @NotEmpty(message = MensagemClienteUtil.CPF_EMPTY)
    @CPF
    private String cpf;

    private Boolean ativo = true;

    public ClienteDTO(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
}
