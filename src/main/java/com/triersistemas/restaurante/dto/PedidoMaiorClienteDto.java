package com.triersistemas.restaurante.dto;

import com.triersistemas.restaurante.entity.PedidoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PedidoMaiorClienteDto {
    private Long idCliente;
    private String nomeCliente;
    private Long idMaiorPedido;
    private String descricao;
    private BigDecimal valor;

    public PedidoMaiorClienteDto(PedidoEntity pedidoEntity) {
        this.idCliente = pedidoEntity.getReserva().getCliente().getId();
        this.nomeCliente = pedidoEntity.getReserva().getCliente().getNome();
        this.idMaiorPedido = pedidoEntity.getId();
        this.descricao = pedidoEntity.getDescricao();
        this.valor = pedidoEntity.getValor();
    }
}
