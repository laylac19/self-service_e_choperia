package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.ClienteCompraProduto;
import com.cssp.choperiaselfservice.service.dto.BuildEmailPurchadeDTO;
import com.cssp.choperiaselfservice.service.dto.ClienteCompraProdutoDTO;
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

    @Query( " SELECT new com.cssp.choperiaselfservice.service.dto.ClienteCompraProdutoDTO(CCP.id, C.id) " +
            " FROM  ClienteCompraProduto        CCP " +
            " JOIN Cliente                      C " +
                " ON CCP.cliente.id = C.id " +
            " WHERE CCP.dataCompra BETWEEN :initialDate AND :finalDate " )
    Set<ClienteCompraProdutoDTO> searchCustomersWhoPurchasedBetweenDates(@Param("initialDate") LocalDate initialDate, @Param("finalDate") LocalDate finalDate);

    @Query( " SELECT new com.cssp.choperiaselfservice.service.dto.BuildEmailPurchadeDTO(" +
                " C.id, C.nome, C.email, " +
                " CCP.id, CCP.dataCompra, CCP.valorCompra) " +
            " FROM  ClienteCompraProduto        CCP " +
            " JOIN Cliente                      C " +
                " ON CCP.cliente.id = C.id " +
            " WHERE C.id = :idClient AND CCP.id = :idPurchased" )
    BuildEmailPurchadeDTO buildEmail(@Param("idClient") Long idClient, @Param("idPurchased") Long idPurchased);
}
