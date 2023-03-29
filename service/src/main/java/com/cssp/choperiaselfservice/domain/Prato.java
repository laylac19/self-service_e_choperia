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
@Table(name = "prato_cozinha")
@Getter
@Setter
public class Prato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_prato")
    @SequenceGenerator(name = "seq_prato", sequenceName = "seq_prato", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "prato", nullable = false)
    private String descricao;

    @Column(name = "preco_kg", nullable = false)
    private Double precoKg;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

}
