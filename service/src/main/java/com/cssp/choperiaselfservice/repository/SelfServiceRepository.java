package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.Prato;
import com.cssp.choperiaselfservice.service.dto.PratoDTO;
import com.cssp.choperiaselfservice.service.dto.PratoListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SelfServiceRepository extends JpaRepository<Prato, Long> {

    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.PratoListDTO(pc.id, pc.descricao, pc.precoKg) " +
            " FROM Prato pc " +
            " WHERE pc.ativo = true ")
    Page<PratoListDTO> listAll(Pageable pageable);

    @Query(" SELECT p.id, p.descricao" +
            " FROM Prato p " +
            " WHERE p.ativo = true AND p.descricao = :description ")
    PratoDTO findDishByDescription(@Param("description") String description);
}
