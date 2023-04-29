package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.Produto;
import com.cssp.choperiaselfservice.service.dto.ChopeListDTO;
import com.cssp.choperiaselfservice.service.dto.ChopeViewDTO;
import com.cssp.choperiaselfservice.service.dto.InsumoListDTO;
import com.cssp.choperiaselfservice.service.dto.ProdutoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.InsumoListDTO(" +
                " P.id, P.qtdeEstoque, P.precoCompra, P.descricao, " +
                " P.unidade, P.pontoEncomenda, P.precoVenda, P.codigoBarras) " +
            " FROM Produto P " +
            " WHERE P.codigoBarras IS NOT NULL " +
                " AND P.ativo = true ")
    Page<InsumoListDTO> listAllInputs(Pageable pageable);

    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.InsumoListDTO(" +
                " P.id, P.qtdeEstoque, P.descricao, P.codigoBarras) " +
            " FROM Produto P " +
            " WHERE P.codigoBarras IS NOT NULL " +
                " AND P.ativo = true " +
                " AND P.codigoBarras LIKE :barCode")
    InsumoListDTO findInputByBarCode(@Param("barCode") String barCode);

    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.ChopeListDTO(P.id, P.qtdeEstoque, " +
            " P.precoCompra, P.descricao, P.unidade, P.pontoEncomenda, P.precoVenda, P.litro_chope, P.etiquetaRFID) " +
            " FROM Produto P " +
            " WHERE P.etiquetaRFID IS NOT NULL " +
                " AND P.ativo = true ")
    Page<ChopeListDTO> listAllBeers(Pageable pageable);

    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.ChopeViewDTO(P.id, " +
            " P.descricao, P.qtdeEstoque, P.precoVenda, P.litro_chope) " +
            " FROM Produto P " +
            " WHERE P.etiquetaRFID IS NOT NULL " +
                " AND P.ativo = true ")
    List<ChopeViewDTO> listAllDraftBeerToClient();

    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.ChopeListDTO(" +
            " P.id, P.qtdeEstoque, P.descricao, P.etiquetaRFID) " +
            " FROM Produto P " +
            " WHERE P.etiquetaRFID IS NOT NULL " +
            " AND P.ativo = true " +
            " AND P.etiquetaRFID LIKE :rfid ")
    ChopeListDTO findDraftBeerByRFID(@Param("rfid") String rfid);
    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.ProdutoDTO(" +
                " P.id, P.descricao, P.unidade, P.precoVenda)" +
            " FROM Produto P" +
            " WHERE P.codigoBarras IS NULL " +
                " AND P.etiquetaRFID IS NULL " +
                " AND P.ativo = true " )
    ProdutoDTO findSelfServiceProduct();

}
