package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.Cliente;
import com.cssp.choperiaselfservice.service.dto.ClienteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends EntityMapper<ClienteDTO, Cliente> {
}
