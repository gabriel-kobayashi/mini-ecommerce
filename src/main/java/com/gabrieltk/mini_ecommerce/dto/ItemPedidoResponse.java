package com.gabrieltk.mini_ecommerce.dto;

import java.math.BigDecimal;

public record ItemPedidoResponse(
        Long id,
        String produto,
        Integer quantidade,
        BigDecimal precoUnitario,
        BigDecimal subtotal
) {
}
