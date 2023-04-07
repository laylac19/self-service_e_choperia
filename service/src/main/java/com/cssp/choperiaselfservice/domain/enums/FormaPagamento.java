package com.cssp.choperiaselfservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FormaPagamento {
    DINHEIRO(0, "Dinheiro"),
    PIX(1, "Pix"),
    PICPAY(2, "PicPay"),
    DEBITO(3, "Cartão Débito"),
    CREDITO(4, "Cartão Crédito");

    private final Integer id;
    private final String value;
}
