package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.ChopeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChopeCompraRepository extends JpaRepository<ChopeCompra, Long> {

}
