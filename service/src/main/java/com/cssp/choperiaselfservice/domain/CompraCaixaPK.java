package com.cssp.choperiaselfservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompraCaixaPK implements Serializable {

    @Column(name = "compra_id")
    private Long idCompra;

    @Column(name = "caixa_id")
    private Long idCaixa;


}
