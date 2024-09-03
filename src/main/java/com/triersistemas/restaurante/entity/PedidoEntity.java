package com.triersistemas.restaurante.entity;

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
    @JoinColumn(name = "reserva_id",nullable = false)
    private ReservaEntity reserva;

    @Column(nullable = false)
    private String nomePrato;
    @Column(nullable = false)
    private BigDecimal valor;


}
