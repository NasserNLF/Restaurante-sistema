package com.triersistemas.restaurante.entity;

import com.triersistemas.restaurante.dto.PedidoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "pedido")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    private ReservaEntity reserva;

    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private BigDecimal valor;

    public PedidoEntity(PedidoDto pedidoDto, ReservaEntity reservaEntity) {
        this.id = pedidoDto.getId();
        this.reserva = reservaEntity;
        this.descricao = pedidoDto.getDescricao();
        this.valor = pedidoDto.getValor();
    }


}
