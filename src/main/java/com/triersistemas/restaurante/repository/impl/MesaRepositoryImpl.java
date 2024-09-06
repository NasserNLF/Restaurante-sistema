package com.triersistemas.restaurante.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.triersistemas.restaurante.dto.MesaDto;
import com.triersistemas.restaurante.entity.QMesaEntity;
import com.triersistemas.restaurante.entity.QReservaEntity;
import com.triersistemas.restaurante.entity.QRestauranteEntity;
import com.triersistemas.restaurante.enuns.StatusReservaEnum;
import com.triersistemas.restaurante.repository.MesaRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public class MesaRepositoryImpl implements MesaRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QRestauranteEntity restaurante = QRestauranteEntity.restauranteEntity;
    final QMesaEntity mesa = QMesaEntity.mesaEntity;
    final QReservaEntity reserva = QReservaEntity.reservaEntity;


    @Override
    public Page<MesaDto> getMesasDisponiveis(Pageable pageable, Long restauranteId, Integer numPessoas, LocalDate data) {
        var query = new JPAQuery<MesaDto>(em);

        query.select(Projections.constructor(MesaDto.class, mesa))
                .distinct()
                .from(restaurante)
                .join(restaurante.mesas, mesa)
                .leftJoin(mesa.reservas, reserva)
                .where(restaurante.id.eq(restauranteId)
                        .and(mesa.capacidadePessoas.goe(numPessoas))
                        .and(reserva.dataReserva.eq(data).not().or(reserva.status.eq(StatusReservaEnum.CANCELADA))));

        query.limit(pageable.getPageSize());
        query.offset(pageable.getOffset());

        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }
}
