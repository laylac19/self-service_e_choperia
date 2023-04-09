package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.NotificacaoSelfServicePrato;
import com.cssp.choperiaselfservice.service.dto.NotificacaoSelfServicePratoListDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoSelfServiceRepository extends JpaRepository<NotificacaoSelfServicePrato, Long> {
    @Query( " SELECT NEW com.cssp.choperiaselfservice.service.dto.NotificacaoSelfServicePratoListDTO(nss.id, " +
            " nss.prato.descricao, nss.statusPrato) " +
            " FROM NotificacaoSelfServicePrato nss WHERE nss.ativo = true " )
    List<NotificacaoSelfServicePratoListDTO> listAll(Pageable pageable);

}
