package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.Prato;
import com.cssp.choperiaselfservice.repository.SelfServiceRepository;
import com.cssp.choperiaselfservice.service.dto.PratoDTO;
import com.cssp.choperiaselfservice.service.dto.PratoListDTO;
import com.cssp.choperiaselfservice.service.exception.EntityNotFoundException;
import com.cssp.choperiaselfservice.service.mapper.PratoMapper;
import com.cssp.choperiaselfservice.service.util.MensagemProdutoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PratoCozinhaService {
    //produto prato
    private final SelfServiceRepository repository;
    private final PratoMapper mapper;

    public Prato findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemProdutoUtil.ENTITY_NOT_FOUND));
    }

    public Page<PratoListDTO> listAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    public PratoDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public PratoDTO save(PratoDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void delete(Long id) {
        Prato product = findEntity(id);
        product.setAtivo(false);
        repository.save(product);
    }

    public Prato findDishByDescription(String description) {
        return mapper.toEntity(repository.findDishByDescription(description));
    }

}
