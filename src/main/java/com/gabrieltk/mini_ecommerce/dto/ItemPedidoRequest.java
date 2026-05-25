package com.gabrieltk.mini_ecommerce.dto;

public record ItemPedidoRequest(
        Long productId,
        Integer quantidade
) {
}
