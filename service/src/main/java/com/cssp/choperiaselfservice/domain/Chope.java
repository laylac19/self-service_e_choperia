package com.cssp.choperiaselfservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "chope")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class Chope extends Produto implements Serializable {

    @Column(name = "etiqueta_rfid", nullable = false)
    private String etiquetaRFID;

}
