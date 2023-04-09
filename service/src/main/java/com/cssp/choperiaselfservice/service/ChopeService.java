package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.Chope;
import com.cssp.choperiaselfservice.repository.ChopeRepository;
import com.cssp.choperiaselfservice.service.dto.ChopeCompraDTO;
import com.cssp.choperiaselfservice.service.dto.ChopeDTO;
import com.cssp.choperiaselfservice.service.dto.ChopeListDTO;
import com.cssp.choperiaselfservice.service.dto.ChopeViewDTO;
import com.cssp.choperiaselfservice.service.exception.EntityNotFoundException;
import com.cssp.choperiaselfservice.service.mapper.ChopeMapper;
import com.cssp.choperiaselfservice.service.util.ConstantesUtil;
import com.cssp.choperiaselfservice.service.util.MensagemProdutoUtil;
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

    private final ChopeRepository repository;
    private final ChopeMapper mapper;

    private final ProdutoService productService;
    private final ClienteCompraService purchaseService;

    public Chope findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemProdutoUtil.ENTITY_NOT_FOUND));
    }

    public Page<ChopeListDTO> listAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    public List<ChopeViewDTO> listAllDraftBeers() {
        return repository.listAllDraftBeer();
    }

    public ChopeDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public ChopeDTO save(ChopeDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void delete(Long id) {
        Chope beer = findEntity(id);
        beer.setAtivo(false);
        repository.save(beer);
    }

    public void enterProduct(ChopeDTO dto) {
        Chope beer = findEntity(dto.getId());
        beer.setQtdeEstoque(beer.getQtdeEstoque() + dto.getQtdeEstoque());
        beer.setPrecoCompra(dto.getPrecoCompra());
        beer.setPrecoVenda(dto.getPrecoVenda());
        repository.save(beer);
    }

    public void withdrawalMovementInChoppStock(Long idBeer) {
        Chope beer = findEntity(idBeer);
        beer.setQtdeEstoque(beer.getQtdeEstoque() - ConstantesUtil.QUANTITY_LITERS_MUG);
        productService.validateStockQuantity(beer.getQtdeEstoque(), beer.getPontoEncomenda());
        repository.save(beer);
    }

    public void removalBeerMugByTheCustomer(ChopeCompraDTO purchaseDTO) {
        purchaseService.findCustomerByID(purchaseDTO.getIdCliente());
        withdrawalMovementInChoppStock(purchaseDTO.getIdChope());
        purchaseService.save(purchaseDTO);
    }
}
