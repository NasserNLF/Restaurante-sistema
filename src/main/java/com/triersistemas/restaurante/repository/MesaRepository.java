package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.entity.MesaEntity;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MesaRepository extends JpaRepository<MesaEntity, Long>, MesaRepositoryCustom {
    Boolean existsByNumeroAndRestaurante(Integer numero, RestauranteEntity restauranteEntity);

}

