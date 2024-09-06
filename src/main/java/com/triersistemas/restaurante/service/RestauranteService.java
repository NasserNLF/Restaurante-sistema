package com.triersistemas.restaurante.service;

import com.triersistemas.restaurante.dto.ReservaDto;
import com.triersistemas.restaurante.dto.RestauranteDto;
import com.triersistemas.restaurante.dto.RestauranteFaturamentoDiaDto;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface RestauranteService {
    RestauranteDto postRestaurante(RestauranteDto restauranteDto);

    List<RestauranteDto> getAllRestaurantes();

    RestauranteDto getRestaurante(Long id);

    RestauranteEntity getRestauranteEntity(Long id);

    RestauranteDto putRestaurante(Long id, RestauranteDto restauranteDto);

    void deleteRestaurante(Long id);

    RestauranteFaturamentoDiaDto getFaturamentoDia(Long idRestaurante, LocalDate data);

    RestauranteFaturamentoDiaDto getMaiorFaturamentoMes(Long idRestaurante, Integer mes);

    Page<RestauranteDto> findAllRestaurantesPage(Pageable pageable, String nome);


}
