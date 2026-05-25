package com.gabrieltk.mini_ecommerce.repository;

import com.gabrieltk.mini_ecommerce.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
