package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.ClienteCompra;
import com.cssp.choperiaselfservice.service.dto.ClienteCompraDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteCompraMapper extends EntityMapper<ClienteCompraDTO, ClienteCompra> {
    @Override
    @Mapping(source = "cliente.id", target = "idCliente")
    ClienteCompraDTO toDto(ClienteCompra entity);

    @Override
    @InheritInverseConfiguration
    ClienteCompra toEntity(ClienteCompraDTO dto);
}
