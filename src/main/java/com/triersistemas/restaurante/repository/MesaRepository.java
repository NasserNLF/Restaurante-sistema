package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.entity.MesaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaRepository extends JpaRepository<MesaEntity, Long> {
}
