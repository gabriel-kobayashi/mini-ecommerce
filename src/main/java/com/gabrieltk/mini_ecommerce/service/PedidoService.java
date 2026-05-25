package com.gabrieltk.mini_ecommerce.service;

import com.gabrieltk.mini_ecommerce.dto.ItemPedidoRequest;
import com.gabrieltk.mini_ecommerce.dto.PedidoRequest;
import com.gabrieltk.mini_ecommerce.dto.PedidoResponse;
import com.gabrieltk.mini_ecommerce.exception.EstoqueInsuficienteException;
import com.gabrieltk.mini_ecommerce.exception.PedidoNotFoundException;
import com.gabrieltk.mini_ecommerce.exception.ProdutoNotFoundException;
import com.gabrieltk.mini_ecommerce.mapper.PedidoMapper;
import com.gabrieltk.mini_ecommerce.model.ItemPedido;
import com.gabrieltk.mini_ecommerce.model.Pedido;
import com.gabrieltk.mini_ecommerce.model.Produto;
import com.gabrieltk.mini_ecommerce.repository.PedidoRepository;
import com.gabrieltk.mini_ecommerce.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public PedidoResponse criarPedido(PedidoRequest request) {
        Pedido pedido = PedidoMapper.toEntity(request);

        List<ItemPedido> itens = new ArrayList<>();

        BigDecimal valorTotal = BigDecimal.ZERO;

        for(ItemPedidoRequest itemRequest: request.itens()) {

            Produto produto = produtoRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));

            if (produto.getEstoque() < itemRequest.quantidade()) {
                throw new EstoqueInsuficienteException("Estoque insuficiente para o produto " + produto.getNome());
            }

            produto.setEstoque(
                    produto.getEstoque() - itemRequest.quantidade()
            );

            ItemPedido itemPedido = new ItemPedido();

            itemPedido.setProduto(produto);

            itemPedido.setQuantidade(itemRequest.quantidade());

            itemPedido.setPrecoUnitario(produto.getPreco());

            BigDecimal subtotal = produto.getPreco()
                    .multiply(BigDecimal.valueOf(itemRequest.quantidade()));

            itemPedido.setSubtotal(subtotal);

            itemPedido.setPedido(pedido);

            itens.add(itemPedido);

            valorTotal = valorTotal.add(subtotal);
        }

        pedido.setItens(itens);

        pedido.setValorTotal(valorTotal);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return PedidoMapper.toResponse(pedidoSalvo);
    }

    public List<PedidoResponse> listar() {
        List<Pedido> pedidos = pedidoRepository.findAll();

        return pedidos.stream()
                .map(PedidoMapper::toResponse)
                .toList();
    }

    public PedidoResponse buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido não encontrado"));

        return PedidoMapper.toResponse(pedido);
    }

    public void removerPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new PedidoNotFoundException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }
}
