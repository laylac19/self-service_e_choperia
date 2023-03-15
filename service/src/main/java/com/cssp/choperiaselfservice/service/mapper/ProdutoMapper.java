package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.Produto;
import com.cssp.choperiaselfservice.service.dto.ProdutoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper extends EntityMapper<ProdutoDTO, Produto> {
    @Override
    ProdutoDTO toDto(Produto entity);

    @Override
    @InheritInverseConfiguration
    Produto toEntity(ProdutoDTO dto);
}
