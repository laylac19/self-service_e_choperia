package com.cssp.choperiaselfservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "chope_compra")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class ChopeCompra extends ClienteCompra implements Serializable {

    @ManyToOne(optional = false)
    @JoinColumn(name = "chope_id", nullable = false)
    private Chope chope;
}
