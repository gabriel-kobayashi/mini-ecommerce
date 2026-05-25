package com.gabrieltk.mini_ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemPedidoRequest(
        @NotNull(message = "Id do produto é obrigatório")
        Long productId,

        @NotNull(message = "Quantidade é obrigatório")
        @Min(value = 1, message = "A quantidade deve ser maior que 0")
        Integer quantidade
) {
}
