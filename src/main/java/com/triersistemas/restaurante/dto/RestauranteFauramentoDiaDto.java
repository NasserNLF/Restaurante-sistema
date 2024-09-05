package com.triersistemas.restaurante.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
public class RestauranteFauramentoDiaDto {

    private Long idRestaurante;
    private LocalDate data;
    private BigDecimal faturamento;


}
