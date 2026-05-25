package com.gabrieltk.mini_ecommerce.dto;



import java.util.List;

public record PedidoRequest(
        List<ItemPedidoRequest> itens
) {
}
