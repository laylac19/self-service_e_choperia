package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long> {

}
