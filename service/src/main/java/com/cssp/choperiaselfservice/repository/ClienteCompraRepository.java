package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.ClienteCompra;
import com.cssp.choperiaselfservice.service.dto.ComprasCaixaListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ClienteCompraRepository extends JpaRepository<ClienteCompra, Long> {

    @Query( " SELECT new com.cssp.choperiaselfservice.service.dto.ComprasCaixaListDTO(" +
                " cc.id, " +
                " CASE WHEN ssc.peso IS NOT NULL THEN 'Self-Service' ELSE ch.chope.descricao END, " +
                " ssc.peso, " +
                " cc.valorCompra) " +
            " FROM Cliente                      c " +
                " JOIN ClienteCompra            cc          ON cc.cliente.id = c.id " +
                " LEFT JOIN ChopeCompra         ch          ON cc.id = ch.id " +
                " LEFT JOIN SelfServiceCompra   ssc         ON cc.id = ssc.id " +
            " WHERE cc.ativo = true AND c.numCartaoRFID = :numCartaoRFID ")
    Set<ComprasCaixaListDTO> listarComprasClientes(@Param("numCartaoRFID") String numCartaoRFID);

}
