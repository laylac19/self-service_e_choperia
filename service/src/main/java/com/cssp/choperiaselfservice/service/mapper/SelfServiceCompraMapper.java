package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.SelfServiceCompra;
import com.cssp.choperiaselfservice.service.dto.SelfServiceCompraDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SelfServiceCompraMapper extends EntityMapper<SelfServiceCompraDTO, SelfServiceCompra> {
    @Override
    @Mapping(source = "cliente.id", target = "idCliente")
    SelfServiceCompraDTO toDto(SelfServiceCompra entity);

    @Override
    @InheritInverseConfiguration
    SelfServiceCompra toEntity(SelfServiceCompraDTO dto);
}
