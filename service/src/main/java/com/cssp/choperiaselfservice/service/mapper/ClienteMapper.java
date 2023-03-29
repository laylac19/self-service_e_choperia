package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.Cliente;
import com.cssp.choperiaselfservice.service.dto.ClienteDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends EntityMapper<ClienteDTO, Cliente> {
    @Override
    ClienteDTO toDto(Cliente entity);

    @Override
    @InheritInverseConfiguration
    Cliente toEntity(ClienteDTO dto);
}
