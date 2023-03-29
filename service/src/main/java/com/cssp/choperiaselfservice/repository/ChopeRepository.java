package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.Chope;
import com.cssp.choperiaselfservice.service.dto.ChopeListDTO;
import com.cssp.choperiaselfservice.service.dto.ChopeViewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChopeRepository extends JpaRepository<Chope, Long> {

    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.ChopeListDTO(c.id, c.qtdeEstoque, " +
            " c.precoCompra, c.descricao, c.unidade, c.pontoEncomenda, c.precoVenda, c.etiquetaRFID) " +
            " FROM Chope c " +
            " WHERE c.ativo = true ")
    Page<ChopeListDTO> listAll(Pageable pageable);

    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.ChopeViewDTO(c.id, " +
            " c.descricao, c.qtdeEstoque, c.precoVenda) " +
            " FROM Chope c " +
            " WHERE c.ativo = true ")
    Page<ChopeViewDTO> listAllDraftBeer(Pageable pageable);
}
