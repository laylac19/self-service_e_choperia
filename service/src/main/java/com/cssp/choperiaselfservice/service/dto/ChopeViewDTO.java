package com.cssp.choperiaselfservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChopeViewDTO implements Serializable {

    private Long id;
    private String descricao;
    private Double qtdeEstoque;
    private Double precoVenda;
    private Double litro_chope;

}
