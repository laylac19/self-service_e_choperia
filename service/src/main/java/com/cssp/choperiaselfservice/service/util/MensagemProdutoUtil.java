package com.cssp.choperiaselfservice.service.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MensagemProdutoUtil {

    public static final String NULL_BARCODE = "Código de Barras Não Pode Ser Nulo";
    public static final String EMPTY_BARCODE = "Código de Barras Não Pode Ser Vazio";
    public static final String NULL_STOCK_QUANTITY = "A Quantidade de Estoque Não Pode Ser Nulo";
    public static final String NULL_PURCHASE_PRICE = "O Preço de Compra Não Pode Ser Nulo";
    public static final String DESCRIPTION_NULL_PRODUCT = "Descrição do Produto Não Pode Ser Nulo";
    public static final String DESCRIPTION_EMPTY_PRODUCT = "Descrição do Produto Não Pode Ser Nulo";
    public static final String NULL_UNIT = "Unidade do Produto Não Pode Ser Nulo";
    public static final String NULL_ORDER_POINT = "Ponto de Encomenda do Produto Não Pode Ser Nulo";
    public static final String NULL_SALE_PRICE = "Preço de Venda do Produto Não Pode Ser Nulo";
    public static final String NULL_PRODUCT_TYPE = "Tipo do Produto Não Pode Ser Nulo";
    public static final String PRODUCT_TYPE_EMPTY = "Tipo do Produto Não Pode Ser Nulo";
    public static final String ENTITY_NOT_FOUND = "Produto Não Encontrado";
    public static final String PRODUCT_REACHED_REORDER_POINT = "Produto Atingiu Ponto de Encomenda. Faça um Novo Pedido!";

    public static final String STOCK_QUANTITY_NOT_BE_NEGATIVE = "A quantidade de Estoque não pode ser negativa";
}
