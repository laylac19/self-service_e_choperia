package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.Perfil;
import com.cssp.choperiaselfservice.service.dto.PerfilDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PerfilMapper extends EntityMapper<PerfilDTO, Perfil> {
    @Override
    PerfilDTO toDto(Perfil entity);

    @Override
    @InheritInverseConfiguration
    Perfil toEntity(PerfilDTO dto);
}
