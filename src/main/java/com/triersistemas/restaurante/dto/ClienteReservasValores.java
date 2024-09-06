package com.triersistemas.restaurante.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClienteReservasValores {

    private Long id;
    private Long qtdReservas;
    private BigDecimal vlrTotal;

}
