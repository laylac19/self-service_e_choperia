package com.cssp.choperiaselfservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "cliente_compra")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class ClienteCompra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_compra")
    @SequenceGenerator(name = "seq_compra", sequenceName = "seq_compra", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "valor_compra", nullable = false)
    private Double valorCompra;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_compra_id", nullable = false)
    private Cliente cliente;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
}
