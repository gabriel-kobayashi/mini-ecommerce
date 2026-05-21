package com.gabrieltk.mini_ecommerce.mapper;

import com.gabrieltk.mini_ecommerce.dto.ProdutoRequest;
import com.gabrieltk.mini_ecommerce.dto.ProdutoResponse;
import com.gabrieltk.mini_ecommerce.model.Produto;

import java.util.List;

public class ProdutoMapper {

    public static Produto toEntity(ProdutoRequest request) {
        Produto produto = new Produto();
        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setEstoque(request.estoque());

        return produto;
    }

    public static ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getEstoque()
        );
    }

    public static List<ProdutoResponse> toResponseList(List<Produto> produtos) {
        return produtos.stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }
}
