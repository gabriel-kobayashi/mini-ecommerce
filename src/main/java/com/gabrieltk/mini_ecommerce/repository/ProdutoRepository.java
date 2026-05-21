package com.gabrieltk.mini_ecommerce.repository;

import com.gabrieltk.mini_ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
