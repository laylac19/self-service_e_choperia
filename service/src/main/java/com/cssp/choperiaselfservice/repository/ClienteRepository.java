package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.Cliente;
import com.cssp.choperiaselfservice.service.dto.ClienteListDTO;
import com.cssp.choperiaselfservice.service.dto.ClienteRelatorioDTO;
import com.cssp.choperiaselfservice.service.dto.ClienteSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.ClienteListDTO(c.id, " +
            " c.numCartaoRFID, c.nome, c.cpf, c.telefone, c.email) " +
            " FROM Cliente c WHERE c.ativo = true" )
    Page<ClienteListDTO> listAll(Pageable pageable);

    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.ClienteListDTO(c.id, " +
            " c.numCartaoRFID, c.nome, c.cpf) " +
            " FROM Cliente c WHERE c.ativo = true AND c.numCartaoRFID IS NOT NULL " )
    List<ClienteListDTO> listCustomersWhoHaveEntered();

    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.ClienteSearchDTO(c.id, " +
            " c.numCartaoRFID, c.nome) " +
            " FROM Cliente c WHERE c.ativo = true AND c.numCartaoRFID LIKE :numCartaoRFID " )
    ClienteSearchDTO findClienteByNumCartaoRFIDLikeAndAtivoIsTrue(@Param("numCartaoRFID") String numCartaoRFID);

    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.ClienteRelatorioDTO(" +
                "C.id, C.nome, C.numCartaoRFID, C.cpf, sum(CCP.valorCompra) AS total_comprado) " +
            " FROM  Cliente                  C " +
            " JOIN ClienteCompraProduto      CCP    ON CCP.cliente.id = C.id " +
            " WHERE CCP.dataCompra BETWEEN :initialDate AND :finalDate " +
            " GROUP BY C.id " +
            " ORDER BY total_comprado ")
    List<ClienteRelatorioDTO> customerReportWithAmountPurchasedInPeriod(@Param("initialDate") LocalDate initialDate,
                                                                        @Param("finalDate") LocalDate finalDate);

    @Query( " SELECT SUM(CCP.valorCompra) " +
            " FROM  Cliente                 C " +
            " JOIN ClienteCompraProduto     CCP " +
            " ON C.id = CCP.cliente.id " +
            " WHERE C.numCartaoRFID = :cardRFID AND CCP.ativo = true" )
    Double checkCustomerCardBalance(@Param("cardRFID") String cardRFID);
}
