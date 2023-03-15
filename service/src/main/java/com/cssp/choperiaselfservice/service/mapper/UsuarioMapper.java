package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.Usuario;
import com.cssp.choperiaselfservice.service.dto.UsuarioDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {
    @Override
    @Mapping(source = "perfil.id", target = "idPerfil")
    @Mapping(source = "perfil.descricao", target = "descPerfil")
    UsuarioDTO toDto(Usuario entity);

    @Override
    @InheritInverseConfiguration
    Usuario toEntity(UsuarioDTO dto);
}
