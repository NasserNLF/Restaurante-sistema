package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.dto.RestauranteFauramentoDiaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface RestauranteRepositoryCustom {
   RestauranteFauramentoDiaDto findFaturamentoByRestauranteIdAndData(Long idRestaurante, LocalDate data);
   RestauranteFauramentoDiaDto findMaiorFaturamentoMesByRestaurante(Long idRestaurante, Integer mes);
}
