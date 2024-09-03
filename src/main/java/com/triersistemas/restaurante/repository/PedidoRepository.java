package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
