package com.cssp.choperiaselfservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "self_service_compra")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class SelfServiceCompra extends ClienteCompra implements Serializable {

    @ManyToOne(optional = false)
    @JoinColumn(name = "prato_cozinha_id", nullable = false)
    private SelfServicePrato pratoCozinha;

    @Column(name = "peso", nullable = false)
    private Double peso;

}
