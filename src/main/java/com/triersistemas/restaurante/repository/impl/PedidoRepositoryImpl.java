package com.triersistemas.restaurante.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.triersistemas.restaurante.dto.PedidoDto;
import com.triersistemas.restaurante.dto.PedidoMaiorClienteDto;
import com.triersistemas.restaurante.entity.*;
import com.triersistemas.restaurante.enuns.StatusReservaEnum;
import com.triersistemas.restaurante.repository.PedidoRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class PedidoRepositoryImpl implements PedidoRepositoryCustom {

    @PersistenceContext
    private EntityManager em;
    final QRestauranteEntity restaurante = QRestauranteEntity.restauranteEntity;
    final QReservaEntity reserva = QReservaEntity.reservaEntity;
    final QPedidoEntity pedido = QPedidoEntity.pedidoEntity;
    final QClienteEntity cliente = QClienteEntity.clienteEntity;

    @Override
    public Page<PedidoMaiorClienteDto> findMaiorPedidoByCliente(Pageable pageable, Long idRestaurante) {
        var query = new JPAQuery<PedidoMaiorClienteDto>(em);

        query.select(Projections.constructor(PedidoMaiorClienteDto.class,
                        cliente.id,
                        cliente.nome,
                        pedido.id,
                        pedido.descricao,
                        pedido.valor.max()))
                .from(pedido)
                .join(pedido.reserva, reserva)
                .join(reserva.cliente, cliente)
                .join(cliente.restaurante, restaurante)
                .where(restaurante.id.eq(idRestaurante))
                .groupBy(cliente.id, cliente.nome)
                .orderBy(pedido.valor.desc());

        query.limit(pageable.getPageSize());
        query.offset(pageable.getOffset());

        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());


    }

    @Override
    public Page<PedidoDto> findPedido(Pageable pageable, Long idRestaurante, LocalDate date, BigDecimal valor, StatusReservaEnum status, Long idCliente) {
        var query = new JPAQuery<PedidoDto>(em);
        BooleanBuilder condicao = new BooleanBuilder();

        if (Objects.nonNull(date)) {
            condicao.and(reserva.dataReserva.eq(date));
        }
        if (Objects.nonNull(valor)) {
            condicao.and(pedido.valor.eq(valor));
        }
        if (Objects.nonNull(idCliente)) {
            condicao.and(cliente.id.eq(idCliente));
        }
        if (Objects.nonNull(status)) {
            condicao.and(reserva.status.eq(status));
        }

        query.select(Projections.constructor(PedidoDto.class, pedido))
                .from(pedido)
                .join(pedido.reserva, reserva)
                .join(reserva.cliente, cliente)
                .join(cliente.restaurante, restaurante)
                .where(condicao)
                .orderBy(pedido.valor.desc());


        query.limit(pageable.getPageSize());
        query.offset(pageable.getOffset());

        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());

    }

    @Override
    public Page<PedidoDto> findAllPedidosPage(Pageable pageable, String descricao, Long idRestaurante) {
        var query = new JPAQuery<PedidoDto>(em);
        BooleanBuilder condicao = new BooleanBuilder(restaurante.id.eq(idRestaurante));

        if (Objects.nonNull(descricao) && !descricao.isEmpty()) {
            condicao.and(pedido.descricao.containsIgnoreCase(descricao));
        }

        query.select(Projections.constructor(PedidoDto.class, pedido))
                .from(pedido)
                .join(pedido.reserva, reserva)
                .join(reserva.cliente, cliente)
                .join(cliente.restaurante, restaurante)
                .where(condicao);

        query.limit(pageable.getPageSize());
        query.offset(pageable.getOffset());

        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());

    }
}
