package com.cssp.choperiaselfservice.service.dto;

import com.cssp.choperiaselfservice.domain.Cliente;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaixaOtimizadoDTO {
    @NotNull
    private Long idClienteResponsavelCompra;

    private ArrayList<Long> idClientesConjuntos = new ArrayList<>();

}
