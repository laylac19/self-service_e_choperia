package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.Insumo;
import com.cssp.choperiaselfservice.service.dto.InsumoListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {

    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.InsumoListDTO(i.id, " +
            " i.qtdeEstoque, i.precoCompra, i.descricao, i.unidade, i.pontoEncomenda, i.precoVenda, i.codigoBarras) " +
            " FROM Insumo i " +
            " WHERE i.ativo = true ")
    Page<InsumoListDTO> listAll(Pageable pageable);

    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.InsumoListDTO(i.id, " +
            " i.qtdeEstoque, i.descricao, i.codigoBarras) " +
            " FROM Insumo i " +
            " WHERE i.ativo = true AND i.codigoBarras LIKE :barCode")
    InsumoListDTO findInputByBarCode(@Param("barCode") String barCode);

}
