package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long>, RestauranteRepositoryCustom {

}
