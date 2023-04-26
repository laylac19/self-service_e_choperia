package com.cssp.choperiaselfservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "etiqueta_rfid")
    private String etiquetaRFID;

    @Column(name = "qtde_estoque")
    private Double qtdeEstoque;

    @Column(name = "preco_compra")
    private Double precoCompra;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "unidade", nullable = false)
    private String unidade;

    @Column(name = "ponto_encomenda")
    private Double pontoEncomenda;

    @Column(name = "preco_venda", nullable = false)
    private Double precoVenda;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

}
