package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.Cliente;
import com.cssp.choperiaselfservice.service.dto.ClienteListDTO;
import com.cssp.choperiaselfservice.service.dto.ClienteSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
            " c.nome, c.numCartaoRFID) " +
            " FROM Cliente c WHERE c.ativo = true AND c.numCartaoRFID LIKE :numCartaoRFID " )
    ClienteSearchDTO findClienteByNumCartaoRFIDLikeAndAtivoIsTrue(@Param("numCartaoRFID") String numCartaoRFID);
}
