package com.gabrieltk.mini_ecommerce.service;

import com.gabrieltk.mini_ecommerce.dto.ProdutoRequest;
import com.gabrieltk.mini_ecommerce.dto.ProdutoResponse;
import com.gabrieltk.mini_ecommerce.exception.CategoriaNotFoundException;
import com.gabrieltk.mini_ecommerce.exception.ProdutoNotFoundException;
import com.gabrieltk.mini_ecommerce.mapper.ProdutoMapper;
import com.gabrieltk.mini_ecommerce.model.Categoria;
import com.gabrieltk.mini_ecommerce.model.Produto;
import com.gabrieltk.mini_ecommerce.repository.CategoriaRepository;
import com.gabrieltk.mini_ecommerce.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository repository, CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    public ProdutoResponse criar(ProdutoRequest request) {
        Categoria categoria = categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada"));

        Produto produto = ProdutoMapper.toEntity(request);

        produto.setCategoria(categoria);

        Produto produtoSalvo = repository.save(produto);

        return ProdutoMapper.toResponse(produtoSalvo);
    }

    public List<ProdutoResponse> listar() {
        List<Produto> produtos = repository.findAll();

        return ProdutoMapper.toResponseList(produtos);
    }

    public ProdutoResponse buscarPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));

        return ProdutoMapper.toResponse(produto);
    }

    public ProdutoResponse atualizar(Long id, ProdutoRequest request) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));

        Categoria categoria = categoriaRepository.findById(request.categoriaId())
                        .orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada"));

        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setEstoque(request.estoque());
        produto.setCategoria(categoria);

        Produto produtoSalvo = repository.save(produto);

        return ProdutoMapper.toResponse(produtoSalvo);
    }

    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new ProdutoNotFoundException("Produto não encontrado");
        }

        repository.deleteById(id);
    }
}
