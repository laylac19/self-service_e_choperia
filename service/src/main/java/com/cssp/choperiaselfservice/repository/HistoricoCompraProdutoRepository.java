package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.HistoricoCompraProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoCompraProdutoRepository extends JpaRepository<HistoricoCompraProduto, Long> {

}
