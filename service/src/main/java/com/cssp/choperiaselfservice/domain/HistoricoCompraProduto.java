package com.cssp.choperiaselfservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "historico_compra_produto")
@Getter
@Setter
public class HistoricoCompraProduto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historico_compra_produto")
    @SequenceGenerator(name = "seq_historico_compra_produto", sequenceName = "seq_historico_compra_produto", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "data_compra", nullable = false)
    private LocalDate data_compra = LocalDate.now();

    @Column(name = "valor_compra", nullable = false)
    private Double valor_compra;

    @Column(name = "qtde_produto_comprado", nullable = false)
    private Double qtde_produto_comprado;

}
