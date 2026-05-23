package com.gabrieltk.mini_ecommerce.mapper;

import com.gabrieltk.mini_ecommerce.dto.CategoriaRequest;
import com.gabrieltk.mini_ecommerce.dto.CategoriaResponse;
import com.gabrieltk.mini_ecommerce.model.Categoria;

import java.util.List;

public class CategoriaMapper {

    public static Categoria toEntity(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNome(request.nome());

        return categoria;
    }

    public static CategoriaResponse toResponse(Categoria categoria) {
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNome()
        );
    }

    public static List<CategoriaResponse> toResponseList(List<Categoria> categorias) {
        return categorias.stream()
                .map(CategoriaMapper::toResponse)
                .toList();
    }
}
