package com.triersistemas.restaurante.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.triersistemas.restaurante.dto.RestauranteDto;
import com.triersistemas.restaurante.dto.RestauranteFaturamentoDiaDto;
import com.triersistemas.restaurante.entity.QMesaEntity;
import com.triersistemas.restaurante.entity.QPedidoEntity;
import com.triersistemas.restaurante.entity.QReservaEntity;
import com.triersistemas.restaurante.entity.QRestauranteEntity;
import com.triersistemas.restaurante.repository.RestauranteRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Objects;

public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QRestauranteEntity restaurante = QRestauranteEntity.restauranteEntity;
    final QReservaEntity reserva = QReservaEntity.reservaEntity;
    final QMesaEntity mesa = QMesaEntity.mesaEntity;
    final QPedidoEntity pedido = QPedidoEntity.pedidoEntity;


    @Override
    public RestauranteFaturamentoDiaDto findFaturamentoByRestauranteIdAndData(Long id, LocalDate data) {
        var query = new JPAQuery<RestauranteFaturamentoDiaDto>(em);

        query.select(Projections.constructor(RestauranteFaturamentoDiaDto.class, restaurante.id, reserva.dataReserva, pedido.valor.sum()))
                .from(restaurante)
                .join(restaurante.mesas, mesa)
                .join(mesa.reservas, reserva)
                .join(reserva.pedidos, pedido)
                .where(restaurante.id.eq(id).and(reserva.dataReserva.eq(data)))
                .groupBy(restaurante.id, reserva.dataReserva);

        return query.fetchOne();
    }

    @Override
    public RestauranteFaturamentoDiaDto findMaiorFaturamentoMesByRestaurante(Long idRestaurante, Integer mes) {
        var query = new JPAQuery<RestauranteFaturamentoDiaDto>(em);

        query.select(Projections.constructor(RestauranteFaturamentoDiaDto.class, restaurante.id, reserva.dataReserva, pedido.valor.max()))
                .from(restaurante)
                .join(restaurante.mesas, mesa)
                .join(mesa.reservas, reserva)
                .join(reserva.pedidos, pedido)
                .where(restaurante.id.eq(idRestaurante)
                        .and(reserva.dataReserva.month().eq(mes)))
                .groupBy(restaurante.id, reserva.dataReserva)
                .orderBy(pedido.valor.sum().desc())
                .limit(1);

        return query.fetchOne();
    }

    @Override
    public Page<RestauranteDto> findAllRestaurantesPage(Pageable pageable, String nome) {
        var query = new JPAQuery<RestauranteDto>(em);
        BooleanBuilder condicao = new BooleanBuilder();

        if (Objects.nonNull(nome) && !nome.isEmpty()) {
            condicao.and(restaurante.nome.containsIgnoreCase(nome));
        }

        query.select(Projections.constructor(RestauranteDto.class, restaurante))
                .from(restaurante)
                .where(condicao);

        query.limit(pageable.getPageSize());
        query.offset(pageable.getOffset());

        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }
}
