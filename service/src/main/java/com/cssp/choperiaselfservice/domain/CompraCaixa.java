package com.cssp.choperiaselfservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "compra_caixa")
@IdClass(CompraCaixaPK.class)
@Embeddable
@Getter
@Setter
public class CompraCaixa {

    @Id
    @Column(name = "compra_id")
    private Long idCompra;

    @Id
    @Column(name = "caixa_id")
    private Long idCaixa;

    @ManyToOne
    @JoinColumn(name = "compra_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ClienteCompraProduto clienteCompraProduto;

    @ManyToOne
    @JoinColumn(name = "caixa_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Caixa caixa;

}
