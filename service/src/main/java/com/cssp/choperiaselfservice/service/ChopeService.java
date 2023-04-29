package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.Produto;
import com.cssp.choperiaselfservice.repository.ProdutoRepository;
import com.cssp.choperiaselfservice.service.dto.ChopeListDTO;
import com.cssp.choperiaselfservice.service.dto.ChopeViewDTO;
import com.cssp.choperiaselfservice.service.dto.ClienteCompraProdutoDTO;
import com.cssp.choperiaselfservice.service.util.ConstantesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChopeService {

    private final ProdutoService productService;
    private final ClienteCompraProdutoService purchaseService;
    private final ProdutoRepository repository;

    public ChopeListDTO findDraftBeerByRFID(String rfid) {
        return repository.findDraftBeerByRFID(rfid);
    }

    public Page<ChopeListDTO> listAllBeers(Pageable pageable) {
        return repository.listAllBeers(pageable);
    }

    public List<ChopeViewDTO> listAllDraftBeers() {
        return repository.listAllDraftBeerToClient();
    }

    public void withdrawalMovementInChoppStock(Long idBeer) {
        Produto beer = productService.findEntity(idBeer);
        beer.setQtdeEstoque(beer.getQtdeEstoque() - ConstantesUtil.QUANTITY_LITERS_MUG);
        productService.validateStockQuantity(beer.getQtdeEstoque(), beer.getPontoEncomenda());
        repository.save(beer);
    }

    public void removalBeerMugByTheCustomer(ClienteCompraProdutoDTO purchaseDTO) {
        purchaseService.findCustomerByID(purchaseDTO.getIdCliente());
        withdrawalMovementInChoppStock(purchaseDTO.getIdProduto());
        purchaseService.save(purchaseDTO);
    }
}
