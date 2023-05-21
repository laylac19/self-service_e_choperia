package com.cssp.choperiaselfservice.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ChopeViewDTO implements Serializable {

    private Long id;
    private String descricao;
    private Double qtdeEstoque;
    private Double precoVenda;
    private Double litro_chope;

    public ChopeViewDTO(Long id, String descricao, Double qtdeEstoque, Double precoVenda, Double litro_chope) {
        this.id = id;
        this.descricao = descricao;
        this.qtdeEstoque = qtdeEstoque;
        this.precoVenda = precoVenda;
        this.litro_chope = litro_chope;
    }
}
