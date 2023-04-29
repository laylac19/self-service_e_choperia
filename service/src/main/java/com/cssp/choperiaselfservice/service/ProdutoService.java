package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.Produto;
import com.cssp.choperiaselfservice.repository.ProdutoRepository;
import com.cssp.choperiaselfservice.service.dto.EntradaProdutoDTO;
import com.cssp.choperiaselfservice.service.dto.InsumoListDTO;
import com.cssp.choperiaselfservice.service.dto.ProdutoDTO;
import com.cssp.choperiaselfservice.service.exception.BusinessRuleException;
import com.cssp.choperiaselfservice.service.exception.EntityNotFoundException;
import com.cssp.choperiaselfservice.service.mapper.ProdutoMapper;
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
public class ProdutoService {
    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public Produto findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemProdutoUtil.ENTITY_NOT_FOUND));
    }

    public ProdutoDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public InsumoListDTO findInputByBarCode(String barCode) {
        return repository.findInputByBarCode(barCode);
    }

    public ProdutoDTO findSelfServiceProduct() {
        return repository.findSelfServiceProduct();
    }

    public ProdutoDTO save(ProdutoDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void delete(Long id) {
        Produto product = findEntity(id);
        product.setAtivo(false);
        repository.save(product);
    }

    public void enterProduct(EntradaProdutoDTO dto) {
        Produto product = findEntity(dto.getId());
        product.setQtdeEstoque(product.getQtdeEstoque() + dto.getQtdeEstoque());
        repository.save(product);
    }

    public void productWithdrawal(EntradaProdutoDTO dto) {
        Produto product = findEntity(dto.getId());
        product.setQtdeEstoque(validadeStockWithdrawal(product.getQtdeEstoque(), dto.getQtdeEstoque()));
        validateStockQuantity(product.getQtdeEstoque(), product.getPontoEncomenda());
        repository.save(product);
    }

    public void enterListOfProducts(Set<EntradaProdutoDTO> productDTOList) {
        if (Objects.isNull(productDTOList)) {
            throw new BusinessRuleException(MensagemProdutoUtil.LIST_NOT_VALID);
        }
        productDTOList.forEach(this::enterProduct);
    }

    public void withdrawalListOfProducts(Set<EntradaProdutoDTO> productDTOList) {
        if (Objects.isNull(productDTOList)) {
            throw new BusinessRuleException(MensagemProdutoUtil.LIST_NOT_VALID);
        }
        productDTOList.forEach(this::productWithdrawal);
    }

    public Double validadeStockWithdrawal(Double productStock, Double dtoStock) {
        if (dtoStock > 0 && productStock >= dtoStock) {
            return productStock - dtoStock;
        }
        throw new BusinessRuleException(MensagemProdutoUtil.STOCK_QUANTITY_NOT_BE_NEGATIVE);
    }

    public void validateStockQuantity(Double stockQuantity, Double pointOrder) {
        if (Objects.equals(stockQuantity, pointOrder)) {
            throw new BusinessRuleException(MensagemProdutoUtil.PRODUCT_REACHED_REORDER_POINT);
        }
    }

    public Page<InsumoListDTO> listAllInputs(Pageable pageable) {
        return repository.listAllInputs(pageable);
    }
}
