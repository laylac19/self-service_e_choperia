package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.Prato;
import com.cssp.choperiaselfservice.service.dto.PratoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PratoMapper extends EntityMapper<PratoDTO, Prato> {
    @Override
    PratoDTO toDto(Prato entity);

    @Override
    @InheritInverseConfiguration
    Prato toEntity(PratoDTO dto);
}
