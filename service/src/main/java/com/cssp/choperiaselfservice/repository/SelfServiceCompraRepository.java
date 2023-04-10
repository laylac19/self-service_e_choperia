package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.SelfServiceCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelfServiceCompraRepository extends JpaRepository<SelfServiceCompra, Long> {

}
