package com.cssp.choperiaselfservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "insumo")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class Insumo extends Produto implements Serializable {

    @Column(name = "codigo_barras", nullable = false)
    private String codigoBarras;

}
