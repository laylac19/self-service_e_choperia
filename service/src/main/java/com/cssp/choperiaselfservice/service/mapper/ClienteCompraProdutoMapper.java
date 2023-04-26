package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.ClienteCompraProduto;
import com.cssp.choperiaselfservice.service.dto.ClienteCompraProdutoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteCompraProdutoMapper extends EntityMapper<ClienteCompraProdutoDTO, ClienteCompraProduto> {
    @Override
    @Mapping(source = "cliente.id", target = "idCliente")
    @Mapping(source = "produto.id", target = "idProduto")
    ClienteCompraProdutoDTO toDto(ClienteCompraProduto entity);

    @Override
    @InheritInverseConfiguration
    ClienteCompraProduto toEntity(ClienteCompraProdutoDTO dto);
}
