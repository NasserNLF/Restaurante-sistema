package com.triersistemas.restaurante.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservaVlrTotalDto {

    private Long idReserva;
    private BigDecimal vlrTotal;


}
