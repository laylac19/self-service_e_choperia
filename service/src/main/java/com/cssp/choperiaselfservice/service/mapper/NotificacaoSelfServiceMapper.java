package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.NotificacaoReposicaoCozinha;
import com.cssp.choperiaselfservice.service.dto.NotificacaoSelfServicePratoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificacaoSelfServiceMapper extends EntityMapper<NotificacaoSelfServicePratoDTO, NotificacaoReposicaoCozinha> {
    @Override
    NotificacaoSelfServicePratoDTO toDto(NotificacaoReposicaoCozinha entity);

    @Override
    @InheritInverseConfiguration
    NotificacaoReposicaoCozinha toEntity(NotificacaoSelfServicePratoDTO dto);
}
