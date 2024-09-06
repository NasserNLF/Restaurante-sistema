package com.triersistemas.restaurante.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.triersistemas.restaurante.dto.ReservaDto;
import com.triersistemas.restaurante.dto.ReservaVlrTotalDto;
import com.triersistemas.restaurante.entity.QClienteEntity;
import com.triersistemas.restaurante.entity.QPedidoEntity;
import com.triersistemas.restaurante.entity.QReservaEntity;
import com.triersistemas.restaurante.entity.QRestauranteEntity;
import com.triersistemas.restaurante.repository.ReservaRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public class ReservaRepositoryImpl implements ReservaRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QReservaEntity reserva = QReservaEntity.reservaEntity;
    final QPedidoEntity pedido = QPedidoEntity.pedidoEntity;
    final QRestauranteEntity restaurante = QRestauranteEntity.restauranteEntity;
    final QClienteEntity cliente = QClienteEntity.clienteEntity;


    @Override
    public ReservaVlrTotalDto findVlrTotalByReserva(Long idRestaurante, Long idReserva) {
        var query = new JPAQuery<ReservaVlrTotalDto>(em);

        query.select(Projections.constructor(ReservaVlrTotalDto.class, reserva.id, pedido.valor.sum()))
                .from(restaurante)
                .join(restaurante.clientes, cliente)
                .join(cliente.reservas, reserva)
                .join(reserva.pedidos, pedido)
                .where(restaurante.id.eq(idRestaurante).and(reserva.id.eq(idReserva)))
                .groupBy(reserva.id);

        return query.fetchOne();

    }

    @Override
    public Page<ReservaDto> findByObservacao(Pageable pageable, Long idRestaurante, String descricao) {
        var query = new JPAQuery<ReservaDto>(em);

        query.select(Projections.constructor(ReservaDto.class, reserva))
                .from(reserva)
                .join(reserva.cliente, cliente)
                .join(cliente.restaurante, restaurante)
                .where(restaurante.id.eq(idRestaurante).and(reserva.observacao.containsIgnoreCase(descricao)));

        query.limit(pageable.getPageSize());
        query.offset(pageable.getOffset());

        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }
}
