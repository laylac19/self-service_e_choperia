package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.ClienteCompraProduto;
import com.cssp.choperiaselfservice.service.dto.BuildEmailPurchadeDTO;
import com.cssp.choperiaselfservice.service.dto.ClienteDTO;
import com.cssp.choperiaselfservice.service.dto.ComprasCaixaListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface ClienteCompraProdutoRepository extends JpaRepository<ClienteCompraProduto, Long> {

    @Query( " SELECT new com.cssp.choperiaselfservice.service.dto.ComprasCaixaListDTO(" +
                " cc.id, cc.produto.descricao, cc.pesoPrato, cc.valorCompra) " +
            " FROM Cliente                             c " +
                " JOIN ClienteCompraProduto            cc          ON cc.cliente.id = c.id " +
            " WHERE cc.ativo = true AND c.numCartaoRFID = :numCartaoRFID ")
    Set<ComprasCaixaListDTO> listPurchasesCustomersCashier(@Param("numCartaoRFID") String numCartaoRFID);

    @Query( " SELECT DISTINCT new com.cssp.choperiaselfservice.service.dto.ClienteDTO(C.id, C.nome, C.email) " +
            " FROM  Cliente                      C " +
            " JOIN ClienteCompraProduto          CCP " +
                " ON C.id = CCP.cliente.id " +
            " WHERE CCP.dataCompra BETWEEN :initialDate AND :finalDate")
    Set<ClienteDTO> searchCustomersWhoPurchasedBetweenDates(@Param("initialDate") LocalDate initialDate, @Param("finalDate") LocalDate finalDate);

}
