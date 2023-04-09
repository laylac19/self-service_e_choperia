package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.Insumo;
import com.cssp.choperiaselfservice.repository.InsumoRepository;
import com.cssp.choperiaselfservice.service.dto.InsumoDTO;
import com.cssp.choperiaselfservice.service.dto.InsumoEntryDTO;
import com.cssp.choperiaselfservice.service.dto.InsumoListDTO;
import com.cssp.choperiaselfservice.service.exception.BusinessRuleException;
import com.cssp.choperiaselfservice.service.exception.EntityNotFoundException;
import com.cssp.choperiaselfservice.service.mapper.InsumoMapper;
import com.cssp.choperiaselfservice.service.util.MensagemProdutoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class InsumoService {
    private final InsumoRepository repository;
    private final InsumoMapper mapper;

    private final ProdutoService productService;

    public Insumo findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemProdutoUtil.ENTITY_NOT_FOUND));
    }

    public Page<InsumoListDTO> listAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    public InsumoDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public InsumoListDTO findInputByBarCode(String barCode) {
        return repository.findInputByBarCode(barCode);
    }

    public InsumoDTO save(InsumoDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void delete(Long id) {
        Insumo product = findEntity(id);
        product.setAtivo(false);
        repository.save(product);
    }

    public void enterListOfProducts(Set<InsumoEntryDTO> productDTOList) {
        if (Objects.isNull(productDTOList)) {
            throw new BusinessRuleException(MensagemProdutoUtil.LIST_NOT_VALID);
        }
        productDTOList.forEach(this::enterProduct);
    }

    public void withdrawalListOfProducts(Set<InsumoEntryDTO> productDTOList) {
        if (Objects.isNull(productDTOList)) {
            throw new BusinessRuleException(MensagemProdutoUtil.LIST_NOT_VALID);
        }
        productDTOList.forEach(this::productWithdrawal);
    }

    public void enterProduct(InsumoEntryDTO dto) {
        Insumo product = findEntity(dto.getId());
        product.setQtdeEstoque(product.getQtdeEstoque() + dto.getQtdeEstoque());
        repository.save(product);
    }

    public void productWithdrawal(InsumoEntryDTO dto) {
        Insumo product = findEntity(dto.getId());
        product.setQtdeEstoque(productService.validadeStockWithdrawal(product.getQtdeEstoque(), dto.getQtdeEstoque()));
        productService.validateStockQuantity(product.getQtdeEstoque(), product.getPontoEncomenda());
        repository.save(product);
    }

}
