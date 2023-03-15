package com.cssp.choperiaselfservice.repository;

import com.cssp.choperiaselfservice.domain.Perfil;
import com.cssp.choperiaselfservice.service.dto.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    @Query(" SELECT NEW com.cssp.choperiaselfservice.service.dto.DropdownDTO(p.descricao, p.id) " +
            " FROM Perfil p WHERE p.ativo = true")
    List<DropdownDTO> profileDropdown();
}
