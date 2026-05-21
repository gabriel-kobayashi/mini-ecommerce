package com.gabrieltk.mini_ecommerce.service;

import com.gabrieltk.mini_ecommerce.dto.ProdutoRequest;
import com.gabrieltk.mini_ecommerce.dto.ProdutoResponse;
import com.gabrieltk.mini_ecommerce.mapper.ProdutoMapper;
import com.gabrieltk.mini_ecommerce.model.Produto;
import com.gabrieltk.mini_ecommerce.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoResponse criar(ProdutoRequest request) {
        Produto produto = ProdutoMapper.toEntity(request);

        Produto produtoSalvo = repository.save(produto);

        return ProdutoMapper.toResponse(produtoSalvo);
    }

    public List<ProdutoResponse> listar() {
        List<Produto> produtos = repository.findAll();

        return ProdutoMapper.toResponseList(produtos);
    }

    public ProdutoResponse buscarPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return ProdutoMapper.toResponse(produto);
    }

    public ProdutoResponse atualizar(Long id, ProdutoRequest request) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setEstoque(request.estoque());

        Produto produtoSalvo = repository.save(produto);

        return ProdutoMapper.toResponse(produtoSalvo);
    }

    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }

        repository.deleteById(id);
    }
}
