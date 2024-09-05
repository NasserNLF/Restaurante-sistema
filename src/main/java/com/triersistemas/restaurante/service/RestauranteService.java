package com.triersistemas.restaurante.service;

import com.triersistemas.restaurante.dto.RestauranteDto;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RestauranteService {
    RestauranteDto postRestaurante(RestauranteDto restauranteDto);

    List<RestauranteDto> getAllRestaurantes();

    RestauranteDto getRestaurante(Long id);

    RestauranteEntity getRestauranteEntity(Long id);

    RestauranteDto putRestaurante(Long id, RestauranteDto restauranteDto);

    void deleteRestaurante(Long id);

    Page<RestauranteDto> getFaturamentoDia(Long idRestaurante, LocalDate data);
    
}
