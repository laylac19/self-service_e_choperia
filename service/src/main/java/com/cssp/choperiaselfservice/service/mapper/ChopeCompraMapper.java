package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.ChopeCompra;
import com.cssp.choperiaselfservice.service.dto.ChopeCompraDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChopeCompraMapper extends EntityMapper<ChopeCompraDTO, ChopeCompra> {
    @Override
    @Mapping(source = "cliente.id", target = "idCliente")
    ChopeCompraDTO toDto(ChopeCompra entity);

    @Override
    @InheritInverseConfiguration
    ChopeCompra toEntity(ChopeCompraDTO dto);
}
