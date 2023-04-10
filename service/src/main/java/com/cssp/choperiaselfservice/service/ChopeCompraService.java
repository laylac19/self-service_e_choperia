package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.ChopeCompra;
import com.cssp.choperiaselfservice.repository.ChopeCompraRepository;
import com.cssp.choperiaselfservice.service.dto.ChopeCompraDTO;
import com.cssp.choperiaselfservice.service.exception.EntityNotFoundException;
import com.cssp.choperiaselfservice.service.mapper.ChopeCompraMapper;
import com.cssp.choperiaselfservice.service.util.MensagemClienteCompraUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChopeCompraService {

    private final ChopeCompraRepository repository;
    private final ChopeCompraMapper mapper;

    public ChopeCompra findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemClienteCompraUtil.ENTITY_NOT_FOUND));
    }

    public ChopeCompraDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public ChopeCompraDTO save(ChopeCompraDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void delete(Long id) {
        ChopeCompra purchase = findEntity(id);
        purchase.setAtivo(false);
        repository.save(purchase);
    }
}
