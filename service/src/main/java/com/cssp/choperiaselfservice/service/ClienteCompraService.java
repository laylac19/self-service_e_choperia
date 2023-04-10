package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.repository.ClienteCompraRepository;
import com.cssp.choperiaselfservice.service.dto.ComprasCaixaListDTO;
import com.cssp.choperiaselfservice.service.exception.BusinessRuleException;
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
    private final ClienteService customerService;

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
