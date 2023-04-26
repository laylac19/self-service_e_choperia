package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.ClienteCompraProduto;
import com.cssp.choperiaselfservice.service.dto.ComprasCaixaListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ClienteCompraProdutoRepository extends JpaRepository<ClienteCompraProduto, Long> {

    @Query( " SELECT new com.cssp.choperiaselfservice.service.dto.ComprasCaixaListDTO(" +
                " cc.id, cc.produto.descricao, cc.pesoPrato, cc.valorCompra) " +
            " FROM Cliente                             c " +
                " JOIN ClienteCompraProduto            cc          ON cc.cliente.id = c.id " +
            " WHERE cc.ativo = true AND c.numCartaoRFID = :numCartaoRFID ")
    Set<ComprasCaixaListDTO> listPurchasesCustomersCashier(@Param("numCartaoRFID") String numCartaoRFID);

}
