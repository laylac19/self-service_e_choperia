package com.cssp.choperiaselfservice.domain;

import com.cssp.choperiaselfservice.domain.enums.StatusPrato;
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
@Table(name = "notificacao_cozinha")
@Getter
@Setter
public class NotificacaoReposicaoCozinha implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_notificacao")
    @SequenceGenerator(name = "seq_notificacao", sequenceName = "seq_notificacao", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "descricao_prato", nullable = false)
    private String descricaoPrato;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_prato", nullable = false)
    private StatusPrato statusPrato;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

}
