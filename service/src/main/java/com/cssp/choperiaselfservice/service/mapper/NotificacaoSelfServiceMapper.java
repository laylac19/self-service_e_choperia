package com.cssp.choperiaselfservice.service.mapper;

import com.cssp.choperiaselfservice.domain.NotificacaoReposicaoCozinha;
import com.cssp.choperiaselfservice.service.dto.NotificacaoSelfServicePratoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificacaoSelfServiceMapper extends EntityMapper<NotificacaoSelfServicePratoDTO, NotificacaoReposicaoCozinha> {
}
