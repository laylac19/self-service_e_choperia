package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.NotificacaoSelfServicePrato;
import com.cssp.choperiaselfservice.service.dto.NotificacaoSelfServicePratoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificacaoSelfServiceMapper extends EntityMapper<NotificacaoSelfServicePratoDTO, NotificacaoSelfServicePrato> {
    @Override
    @Mapping(source = "prato.id", target = "idPrato")
    NotificacaoSelfServicePratoDTO toDto(NotificacaoSelfServicePrato entity);

    @Override
    @InheritInverseConfiguration
    NotificacaoSelfServicePrato toEntity(NotificacaoSelfServicePratoDTO dto);
}
