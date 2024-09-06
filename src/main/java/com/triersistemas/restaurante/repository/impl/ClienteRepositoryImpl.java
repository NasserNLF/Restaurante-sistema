package com.triersistemas.restaurante.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.triersistemas.restaurante.dto.ClienteReservasValores;
import com.triersistemas.restaurante.dto.ReservaVlrTotalDto;
import com.triersistemas.restaurante.entity.QClienteEntity;
import com.triersistemas.restaurante.entity.QPedidoEntity;
import com.triersistemas.restaurante.entity.QReservaEntity;
import com.triersistemas.restaurante.entity.QRestauranteEntity;
import com.triersistemas.restaurante.enuns.StatusReservaEnum;
import com.triersistemas.restaurante.repository.ClienteRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ClienteRepositoryImpl implements ClienteRepositoryCustom {
    @PersistenceContext
    private EntityManager em;


    final QRestauranteEntity restaurante = QRestauranteEntity.restauranteEntity;
    final QClienteEntity cliente = QClienteEntity.clienteEntity;
    final QReservaEntity reserva = QReservaEntity.reservaEntity;
    final QPedidoEntity pedido = QPedidoEntity.pedidoEntity;


    @Override
    public Page<ClienteReservasValores> findReservasAndValoresByCliente(Pageable pageable, Long idRestaurante) {
        var query = new JPAQuery<ClienteReservasValores>(em);


        query.select(Projections.constructor(ClienteReservasValores.class, cliente.id, reserva.status.eq(StatusReservaEnum.CONCLUIDA).count(), pedido.valor.sum()))
                .from(restaurante)
                .join(restaurante.clientes, cliente)
                .join(cliente.reservas, reserva)
                .join(reserva.pedidos, pedido)
                .where(restaurante.id.eq(idRestaurante))
                .groupBy(cliente.id)
                .orderBy(pedido.valor.sum().desc());

        query.limit(pageable.getPageSize());
        query.offset(pageable.getOffset());

        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());

    }
}
