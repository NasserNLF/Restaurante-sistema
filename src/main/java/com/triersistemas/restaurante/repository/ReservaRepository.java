package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
}
