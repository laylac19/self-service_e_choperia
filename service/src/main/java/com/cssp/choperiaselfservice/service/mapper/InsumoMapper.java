package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.Insumo;
import com.cssp.choperiaselfservice.service.dto.InsumoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InsumoMapper extends EntityMapper<InsumoDTO, Insumo> {
    @Override
    InsumoDTO toDto(Insumo entity);

    @Override
    @InheritInverseConfiguration
    Insumo toEntity(InsumoDTO dto);
}
