package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.ClienteCompra;
import com.cssp.choperiaselfservice.repository.ClienteCompraRepository;
import com.cssp.choperiaselfservice.service.dto.ClienteCompraDTO;
import com.cssp.choperiaselfservice.service.dto.ComprasCaixaListDTO;
import com.cssp.choperiaselfservice.service.exception.BusinessRuleException;
import com.cssp.choperiaselfservice.service.exception.EntityNotFoundException;
import com.cssp.choperiaselfservice.service.mapper.ClienteCompraMapper;
import com.cssp.choperiaselfservice.service.util.MensagemClienteCompraUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteCompraService {

    private final ClienteCompraRepository repository;
    private final ClienteCompraMapper mapper;
    private final ClienteService customerService;

    public ClienteCompra findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemClienteCompraUtil.ENTITY_NOT_FOUND));
    }

    public ClienteCompraDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public ClienteCompraDTO save(ClienteCompraDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void delete(Long id) {
        ClienteCompra purchase = findEntity(id);
        purchase.setAtivo(false);
        repository.save(purchase);
    }

    public void findCustomerByID(Long idCustomer) {
        customerService.findEntity(idCustomer);
    }

    public Set<ComprasCaixaListDTO> listPurchasedItemsOfCustomer(String codClientRFID) {
        Set<ComprasCaixaListDTO> purchasesMade = repository.listarComprasClientes(codClientRFID);
        if (Objects.nonNull(purchasesMade)) {
            return purchasesMade;
        }
        throw new BusinessRuleException(MensagemClienteCompraUtil.NULL_PURCHASES_MADE);
    }

}
