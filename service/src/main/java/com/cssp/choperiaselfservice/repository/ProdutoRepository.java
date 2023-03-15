package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.Produto;
import com.cssp.choperiaselfservice.service.dto.ProdutoListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query(" SELECT NEW com.cssp.choperiaselfservice.service.dto.ProdutoListDTO(p.id, " +
            " p.codigoBarras, p.qtdeEstoque, p.precoCompra, p.descricao, p.unidade, p.pontoEncomenda, p.precoVenda," +
            " p.tipoProduto) " +
            " FROM Produto p WHERE p.ativo = true ")
    Page<ProdutoListDTO> listAll(Pageable pageable);
}
