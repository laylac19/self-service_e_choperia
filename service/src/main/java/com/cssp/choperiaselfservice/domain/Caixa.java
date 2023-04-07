package com.cssp.choperiaselfservice.domain;

import com.cssp.choperiaselfservice.domain.enums.FormaPagamento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contas_clientes")
    private Set<Cliente> clientes = new LinkedHashSet<>();

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
