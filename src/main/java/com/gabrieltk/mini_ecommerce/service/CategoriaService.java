package com.gabrieltk.mini_ecommerce.service;

import com.gabrieltk.mini_ecommerce.dto.CategoriaRequest;
import com.gabrieltk.mini_ecommerce.dto.CategoriaResponse;
import com.gabrieltk.mini_ecommerce.exception.CategoriaNotFoundException;
import com.gabrieltk.mini_ecommerce.mapper.CategoriaMapper;
import com.gabrieltk.mini_ecommerce.model.Categoria;
import com.gabrieltk.mini_ecommerce.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public CategoriaResponse criar(CategoriaRequest request) {
        Categoria categoria = CategoriaMapper.toEntity(request);

        Categoria categoriaSalva = repository.save(categoria);

        return CategoriaMapper.toResponse(categoriaSalva);
    }

    public List<CategoriaResponse> listar() {
        List<Categoria> categorias = repository.findAll();
        return CategoriaMapper.toResponseList(categorias);
    }

    public CategoriaResponse buscarPorId(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada"));

        return CategoriaMapper.toResponse(categoria);
    }

    public CategoriaResponse atualizar(Long id, CategoriaRequest request) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada"));

        categoria.setNome(request.nome());

        Categoria categoriaSalva = repository.save(categoria);

        return CategoriaMapper.toResponse(categoriaSalva);
    }

    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new CategoriaNotFoundException("Categoria não encontrada");
        }
        repository.deleteById(id);
    }
}
