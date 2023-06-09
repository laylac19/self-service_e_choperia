package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.NotificacaoReposicaoCozinha;
import com.cssp.choperiaselfservice.domain.enums.StatusPrato;
import com.cssp.choperiaselfservice.repository.NotificacaoSelfServiceRepository;
import com.cssp.choperiaselfservice.service.dto.NotificacaoSelfServicePratoDTO;
import com.cssp.choperiaselfservice.service.dto.NotificacaoSelfServicePratoListDTO;
import com.cssp.choperiaselfservice.service.mapper.NotificacaoSelfServiceMapper;
import com.cssp.choperiaselfservice.service.util.MensagemCozinhaUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CozinhaService {
    private final NotificacaoSelfServiceRepository repository;
    private final NotificacaoSelfServiceMapper mapper;


    public NotificacaoReposicaoCozinha findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemCozinhaUtil.ENTITY_NOT_FOUND));
    }

    public List<NotificacaoSelfServicePratoListDTO> listAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    public NotificacaoSelfServicePratoDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public void delete(Long id) {
        NotificacaoReposicaoCozinha notification = findEntity(id);
        notification.setAtivo(false);
        repository.save(notification);
    }

    public void needToReplacePlate(String dish) {
        NotificacaoReposicaoCozinha notification = new NotificacaoReposicaoCozinha();
        notification.setDescricaoPrato(dish);
        notification.setStatusPrato(StatusPrato.PENDENTE);
        notification.setAtivo(true);
        repository.save(notification);
    }

    public void replacePlate(Long idNotification) {
        NotificacaoReposicaoCozinha notification = findEntity(idNotification);
        notification.setStatusPrato(StatusPrato.PRONTO);
        notification.setAtivo(false);
        repository.save(notification);
    }

}
