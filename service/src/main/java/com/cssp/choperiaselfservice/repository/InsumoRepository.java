package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.Insumo;
import com.cssp.choperiaselfservice.service.dto.InsumoListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {

    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.InsumoListDTO(i.id, " +
            " i.qtdeEstoque, i.precoCompra, i.descricao, i.unidade, i.pontoEncomenda, i.precoVenda, i.codigoBarras) " +
            " FROM Insumo i " +
            " WHERE i.ativo = true ")
    Page<InsumoListDTO> listAll(Pageable pageable);
}
