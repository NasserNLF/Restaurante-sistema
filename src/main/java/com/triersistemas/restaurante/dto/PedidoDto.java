package com.triersistemas.restaurante.dto;

import com.triersistemas.restaurante.entity.PedidoEntity;
import com.triersistemas.restaurante.entity.ReservaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PedidoDto {

    private Long id;

    private Long reservaId;

    private String descricao;
    private BigDecimal valor;

    public PedidoDto(PedidoEntity pedidoEntity) {
        this.id = pedidoEntity.getId();
        this.reservaId = pedidoEntity.getReserva().getId();
        this.descricao = pedidoEntity.getDescricao();
        this.valor = pedidoEntity.getValor();
    }


}
