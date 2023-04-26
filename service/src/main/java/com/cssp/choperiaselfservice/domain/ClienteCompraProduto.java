package com.cssp.choperiaselfservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "cliente_compra_produto")
@Getter
@Setter
public class ClienteCompraProduto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_compra")
    @SequenceGenerator(name = "seq_compra", sequenceName = "seq_compra", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "valor_compra", nullable = false)
    private Double valorCompra;

    @Column(name = "peso_prato")
    private Double pesoPrato;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

}
