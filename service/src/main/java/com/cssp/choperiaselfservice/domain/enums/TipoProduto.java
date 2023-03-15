package com.cssp.choperiaselfservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoProduto {
    INSUMO(0, "Insumo"),
    SELF_SERVICE(1, "Self-Service"),
    CHOP(2, "Chop");

    private final Integer id;
    private final String value;
}
