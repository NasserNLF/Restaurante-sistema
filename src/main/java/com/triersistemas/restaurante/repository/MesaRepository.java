package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.entity.MesaEntity;
import com.triersistemas.restaurante.entity.ReservaEntity;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaRepository extends JpaRepository<MesaEntity, Long> {
    Boolean existsByNumeroAndRestaurante(Integer numero, RestauranteEntity restauranteEntity);
}
