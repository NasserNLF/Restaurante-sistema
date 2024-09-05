package com.triersistemas.restaurante.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.triersistemas.restaurante.dto.RestauranteFauramentoDiaDto;
import com.triersistemas.restaurante.entity.QMesaEntity;
import com.triersistemas.restaurante.entity.QPedidoEntity;
import com.triersistemas.restaurante.entity.QReservaEntity;
import com.triersistemas.restaurante.entity.QRestauranteEntity;
import com.triersistemas.restaurante.repository.RestauranteRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QRestauranteEntity restaurante = QRestauranteEntity.restauranteEntity;
    final QReservaEntity reserva = QReservaEntity.reservaEntity;
    final QMesaEntity mesa = QMesaEntity.mesaEntity;
    final QPedidoEntity pedido =  QPedidoEntity.pedidoEntity;


    @Override
    public RestauranteFauramentoDiaDto findFaturamentoByRestauranteIdAndData(Long id,LocalDate data) {
        var query = new JPAQuery<RestauranteFauramentoDiaDto>(em);

        query.select(Projections.constructor(RestauranteFauramentoDiaDto.class, restaurante.id, reserva.dataReserva ,pedido.valor.sum()))
                .from(restaurante)
                .join(restaurante.mesas, mesa)
                .join(mesa.reservas, reserva)
                .join(reserva.pedidos, pedido)
                .where(restaurante.id.eq(id).and(reserva.dataReserva.eq(data)))
                .groupBy(pedido);

        return query.fetch();
    }

    @Override
    public RestauranteFauramentoDiaDto findMaiorFaturamentoMesByRestaurante(Long idRestaurante, Integer mes) {
        var query = new JPAQuery<RestauranteFauramentoDiaDto>(em);

        query.select(Projections.constructor(RestauranteFauramentoDiaDto.class, restaurante.id, reserva.dataReserva, pedido.valor.sum()))
                .from(restaurante)
                .join(restaurante.mesas, mesa)
                .join(mesa.reservas, reserva)
                .join(reserva.pedidos, pedido)
                .where(restaurante.id.eq(idRestaurante)
                        .and(reserva.dataReserva.month().eq(mes))
                        .and())



    }
}
