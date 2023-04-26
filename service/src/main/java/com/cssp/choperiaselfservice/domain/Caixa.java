package com.cssp.choperiaselfservice.domain;

import com.cssp.choperiaselfservice.domain.enums.FormaPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "caixa")
@Getter
@Setter
public class Caixa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_caixa")
    @SequenceGenerator(name = "seq_caixa", sequenceName = "seq_caixa", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private ClienteCompraProduto clienteCompraProduto;

    @Column(name = "id_cliente_principal", nullable = false)
    private Long idClientePrincipal;

    @Column(name = "total_conta", nullable = false)
    private Double totalConta;

    @Column(name = "desconto")
    private Double desconto;

    @Column(name = "valor_final", nullable = false)
    private Double valorFinal;

    @Enumerated
    @Column(name = "forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

}
