package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.entity.MesaEntity;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MesaRepository extends JpaRepository<MesaEntity, Long>, MesaRepositoryCustom{
    Boolean existsByNumeroAndRestaurante(Integer numero, RestauranteEntity restauranteEntity);

    @Query("SELECT m FROM mesa m WHERE m.capacidadePessoas >= :qtdPessoas AND m.restaurante.id = :restauranteId AND m.id NOT IN (SELECT r.mesa.id FROM reserva r WHERE r.dataReserva = :data)")
    List<MesaEntity> findMesasDisponiveis(@Param("data") LocalDate data, @Param("qtdPessoas") Integer qtdPessoas, @Param("restauranteId") Long idRestaurante);
}

