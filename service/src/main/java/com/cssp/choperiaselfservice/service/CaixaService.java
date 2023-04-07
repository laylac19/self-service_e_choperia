package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.Caixa;
import com.cssp.choperiaselfservice.repository.CaixaRepository;
import com.cssp.choperiaselfservice.service.dto.CaixaDTO;
import com.cssp.choperiaselfservice.service.mapper.CaixaMapper;
import com.cssp.choperiaselfservice.service.util.CaixaUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CaixaService {

    private final CaixaRepository repository;
    private final CaixaMapper mapper;

    private final ClienteService customerService;

    private Caixa findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(CaixaUtil.ENTITY_NOT_FOUND));
    }

    public CaixaDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public CaixaDTO save(CaixaDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void delete(Long id) {
        Caixa cashier = findEntity(id);
        cashier.setAtivo(false);
        repository.save(cashier);
    }

    public void finalizeSale(CaixaDTO dto) {
        dto.getClientes().forEach(customer -> customerService.unlinkCardWithCustomer(customer.getId()));
    }

}
