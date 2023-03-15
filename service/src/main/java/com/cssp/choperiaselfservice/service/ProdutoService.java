package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.Produto;
import com.cssp.choperiaselfservice.repository.ProdutoRepository;
import com.cssp.choperiaselfservice.service.dto.ProdutoDTO;
import com.cssp.choperiaselfservice.service.dto.ProdutoListDTO;
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

    public Page<ProdutoListDTO> findAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    public ProdutoDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public ProdutoDTO save(ProdutoDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void delete(Long id) {
        Produto user = findEntity(id);
        user.setAtivo(false);
        repository.save(user);
    }

    public void enterProduct(ProdutoDTO dto) {
        Produto product = findEntity(dto.getId());
        product.setQtdeEstoque(product.getQtdeEstoque() + dto.getQtdeEstoque());
        product.setPrecoCompra(dto.getPrecoCompra());
        product.setPrecoVenda(dto.getPrecoVenda());
        repository.save(product);
    }

    public void productWithdrawal(ProdutoDTO dto) {
        Produto product = findEntity(dto.getId());
        product.setQtdeEstoque(validadeStockWithdrawal(product.getQtdeEstoque(), dto.getQtdeEstoque()));
        validateStockQuantity(product.getQtdeEstoque(), product.getPontoEncomenda());
        repository.save(product);
    }

    private Integer validadeStockWithdrawal(Integer productStock, Integer dtoStock) {
        if (dtoStock > 0 && productStock >= dtoStock) {
            return productStock - dtoStock;
        }
        throw new BusinessRuleException(MensagemProdutoUtil.STOCK_QUANTITY_NOT_BE_NEGATIVE);
    }

    private void validateStockQuantity(Integer stockQuantity, Integer pointOrder) {
        if (Objects.equals(stockQuantity, pointOrder)) {
            throw new BusinessRuleException(MensagemProdutoUtil.PRODUCT_REACHED_REORDER_POINT);
        }
    }
}
