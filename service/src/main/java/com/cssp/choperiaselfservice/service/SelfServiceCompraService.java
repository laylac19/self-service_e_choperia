package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.SelfServiceCompra;
import com.cssp.choperiaselfservice.repository.SelfServiceCompraRepository;
import com.cssp.choperiaselfservice.service.dto.SelfServiceCompraDTO;
import com.cssp.choperiaselfservice.service.exception.EntityNotFoundException;
import com.cssp.choperiaselfservice.service.mapper.SelfServiceCompraMapper;
import com.cssp.choperiaselfservice.service.util.MensagemClienteCompraUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SelfServiceCompraService {

    private final SelfServiceCompraRepository repository;
    private final SelfServiceCompraMapper mapper;

    public SelfServiceCompra findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemClienteCompraUtil.ENTITY_NOT_FOUND));
    }

    public SelfServiceCompraDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public SelfServiceCompraDTO save(SelfServiceCompraDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void delete(Long id) {
        SelfServiceCompra purchase = findEntity(id);
        purchase.setAtivo(false);
        repository.save(purchase);
    }

}
