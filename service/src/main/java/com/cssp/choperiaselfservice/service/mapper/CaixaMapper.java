package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.Caixa;
import com.cssp.choperiaselfservice.service.dto.CaixaDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CaixaMapper extends EntityMapper<CaixaDTO, Caixa> {
    @Override
    CaixaDTO toDto(Caixa entity);

    @Override
    @InheritInverseConfiguration
    Caixa toEntity(CaixaDTO dto);
}
