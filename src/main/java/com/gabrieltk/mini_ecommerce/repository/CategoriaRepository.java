package com.gabrieltk.mini_ecommerce.repository;

import com.gabrieltk.mini_ecommerce.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
