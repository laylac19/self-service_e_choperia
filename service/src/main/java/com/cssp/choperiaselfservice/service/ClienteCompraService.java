package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.ClienteCompraProduto;
import com.cssp.choperiaselfservice.repository.ClienteCompraProdutoRepository;
import com.cssp.choperiaselfservice.service.dto.ClienteCompraProdutoDTO;
import com.cssp.choperiaselfservice.service.dto.ComprasCaixaListDTO;
import com.cssp.choperiaselfservice.service.exception.BusinessRuleException;
import com.cssp.choperiaselfservice.service.exception.EntityNotFoundException;
import com.cssp.choperiaselfservice.service.mapper.ClienteCompraProdutoMapper;
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

    private final ClienteCompraProdutoRepository repository;
    private final ClienteService customerService;
    private final ClienteCompraProdutoMapper mapper;


    public ClienteCompraProduto findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemClienteCompraUtil.ENTITY_NOT_FOUND));
    }

    public ClienteCompraProdutoDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public void findCustomerByID(Long idCustomer) {
        customerService.findEntity(idCustomer);
    }

    public Set<ComprasCaixaListDTO> listPurchasedItemsOfCustomer(String codClientRFID) {
        Set<ComprasCaixaListDTO> purchasesMade = repository.listPurchasesCustomersCashier(codClientRFID);
        if (Objects.nonNull(purchasesMade)) {
            return purchasesMade;
        }
        throw new BusinessRuleException(MensagemClienteCompraUtil.NULL_PURCHASES_MADE);
    }

    public ClienteCompraProdutoDTO save(ClienteCompraProdutoDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void delete(Long id) {
        ClienteCompraProduto purchase = findEntity(id);
        purchase.setAtivo(false);
        repository.save(purchase);
    }

}
