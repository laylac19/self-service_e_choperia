package com.cssp.choperiaselfservice.domain;

import com.cssp.choperiaselfservice.domain.enums.TipoProduto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "produto")
@Getter
@Setter
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
    @SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "codigoBarras", nullable = false)
    private String codigoBarras;

    @Column(name = "qtdeEstoque", nullable = false)
    private Integer qtdeEstoque;

    @Column(name = "precoCompra", nullable = false)
    private Double precoCompra;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "unidade", nullable = false)
    private String unidade;

    @Column(name = "pontoEncomenda", nullable = false)
    private Integer pontoEncomenda;

    @Column(name = "precoVenda", nullable = false)
    private Double precoVenda;

    @Column(name = "tipoProduto", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoProduto tipoProduto;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

}
