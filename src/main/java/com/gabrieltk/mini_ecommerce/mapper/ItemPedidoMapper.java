package com.gabrieltk.mini_ecommerce.mapper;

import com.gabrieltk.mini_ecommerce.dto.ItemPedidoResponse;
import com.gabrieltk.mini_ecommerce.model.ItemPedido;

public class ItemPedidoMapper {

    public static ItemPedidoResponse toResponse(ItemPedido itemPedido) {
        return new ItemPedidoResponse(
                itemPedido.getId(),
                itemPedido.getProduto().getNome(),
                itemPedido.getQuantidade(),
                itemPedido.getPrecoUnitario(),
                itemPedido.getSubtotal()
        );
    }
}
