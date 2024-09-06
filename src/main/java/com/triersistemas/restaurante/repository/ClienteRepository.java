package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>, ClienteRepositoryCustom {
}
