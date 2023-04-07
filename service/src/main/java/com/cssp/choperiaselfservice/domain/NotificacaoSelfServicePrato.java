package com.cssp.choperiaselfservice.domain;

import com.cssp.choperiaselfservice.domain.enums.StatusPrato;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "notificacao_cozinha")
@Getter
@Setter
public class NotificacaoSelfServicePrato implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_prato")
    @SequenceGenerator(name = "seq_prato", sequenceName = "seq_prato", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch =  FetchType.LAZY, cascade = CascadeType.DETACH, optional = false, orphanRemoval = true)
    @JoinColumn(name = "prato_id", nullable = false)
    private Prato prato;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_prato", nullable = false)
    private StatusPrato statusPrato;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;



}
