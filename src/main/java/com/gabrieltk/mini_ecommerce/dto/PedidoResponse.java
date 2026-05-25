package com.gabrieltk.mini_ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(
        Long id,
        LocalDateTime dataPedido,
        BigDecimal valorTotal,
        List<ItemPedidoResponse> itens
) {
}
