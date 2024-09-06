package com.triersistemas.restaurante.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestauranteFaturamentoDiaDto {

    private Long idRestaurante;
    private LocalDate data;
    private BigDecimal faturamento;


}
