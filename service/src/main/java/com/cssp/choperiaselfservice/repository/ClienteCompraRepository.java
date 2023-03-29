package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.ClienteCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteCompraRepository extends JpaRepository<ClienteCompra, Long> {
}
