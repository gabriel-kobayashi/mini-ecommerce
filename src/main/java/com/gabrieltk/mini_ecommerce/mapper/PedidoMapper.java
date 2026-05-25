package com.gabrieltk.mini_ecommerce.mapper;

import com.gabrieltk.mini_ecommerce.dto.ItemPedidoResponse;
import com.gabrieltk.mini_ecommerce.dto.PedidoRequest;
import com.gabrieltk.mini_ecommerce.dto.PedidoResponse;
import com.gabrieltk.mini_ecommerce.model.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoMapper {

    public static Pedido toEntity(PedidoRequest request) {
        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDateTime.now());

        return pedido;
    }

    public static PedidoResponse toResponse(Pedido pedido) {
        List<ItemPedidoResponse> itens =
                pedido.getItens()
                        .stream()
                        .map(ItemPedidoMapper::toResponse)
                        .toList();

        return new PedidoResponse(
                pedido.getId(),
                pedido.getDataPedido(),
                pedido.getValorTotal(),
                itens
        );
    }
}
