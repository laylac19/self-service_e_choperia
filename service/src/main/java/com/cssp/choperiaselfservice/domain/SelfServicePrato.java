package com.cssp.choperiaselfservice.domain;

import com.cssp.choperiaselfservice.domain.enums.StatusPrato;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "self_service_prato")
@Getter
@Setter
public class SelfServicePrato implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_prato")
    @SequenceGenerator(name = "seq_prato", sequenceName = "seq_prato", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "prato", nullable = false)
    private String descricao;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_prato", nullable = false)
    private StatusPrato statusPrato;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @OneToMany(mappedBy = "selfServicePrato", orphanRemoval = true)
    private Set<Insumo> insumoes = new LinkedHashSet<>();

}
