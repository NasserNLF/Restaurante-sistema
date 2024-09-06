package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.dto.RestauranteDto;
import com.triersistemas.restaurante.dto.RestauranteFaturamentoDiaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface RestauranteRepositoryCustom {
    RestauranteFaturamentoDiaDto findFaturamentoByRestauranteIdAndData(Long idRestaurante, LocalDate data);

    RestauranteFaturamentoDiaDto findMaiorFaturamentoMesByRestaurante(Long idRestaurante, Integer mes);

    Page<RestauranteDto> findAllRestaurantesPage(Pageable pageable, String nome);
}
