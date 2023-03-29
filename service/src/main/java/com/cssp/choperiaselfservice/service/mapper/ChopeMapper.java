package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.Chope;
import com.cssp.choperiaselfservice.service.dto.ChopeDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChopeMapper extends EntityMapper<ChopeDTO, Chope> {
    @Override
    ChopeDTO toDto(Chope entity);

    @Override
    @InheritInverseConfiguration
    Chope toEntity(ChopeDTO dto);
}
