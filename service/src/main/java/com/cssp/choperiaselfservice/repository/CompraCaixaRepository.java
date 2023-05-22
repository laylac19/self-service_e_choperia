package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.Caixa;
import com.cssp.choperiaselfservice.domain.CompraCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraCaixaRepository extends JpaRepository<CompraCaixa, Long> {

}
