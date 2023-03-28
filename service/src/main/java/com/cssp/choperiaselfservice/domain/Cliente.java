package com.cssp.choperiaselfservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente")
    @SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "numCartaoRFID")
    private String numCartaoRFID;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true)
    private Set<ClienteCompra> cliente_compra = new LinkedHashSet<>();

}
