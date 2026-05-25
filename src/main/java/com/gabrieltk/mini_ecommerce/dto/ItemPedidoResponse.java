package com.gabrieltk.mini_ecommerce.dto;

import java.math.BigDecimal;

public record ItemPedidoResponse(
        String produto,
        Integer quantidade,
        BigDecimal precoUnitario
) {
}
