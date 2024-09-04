package com.triersistemas.restaurante.service;

import com.triersistemas.restaurante.dto.RestauranteDto;
import com.triersistemas.restaurante.entity.RestauranteEntity;

import java.util.List;

public interface RestauranteService {
    RestauranteDto postRestaurante(RestauranteDto restauranteDto);

    List<RestauranteDto> getAllRestaurantes();

    RestauranteDto getRestaurante(Long id);

    RestauranteEntity getRestauranteEntity(Long id);

    RestauranteDto putRestaurante(Long id, RestauranteDto restauranteDto);

    void deleteRestaurante(Long id);


}
