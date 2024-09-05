package com.triersistemas.restaurante.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.triersistemas.restaurante.dto.MesaDto;
import com.triersistemas.restaurante.entity.MesaEntity;
import com.triersistemas.restaurante.entity.QMesaEntity;
import com.triersistemas.restaurante.entity.QReservaEntity;
import com.triersistemas.restaurante.entity.QRestauranteEntity;
import com.triersistemas.restaurante.enuns.StatusReservaEnum;
import com.triersistemas.restaurante.repository.MesaRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.expression.spel.ast.Projection;

import java.time.LocalDate;
import java.util.List;

public class MesaRepositoryImpl implements MesaRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QRestauranteEntity restaurante = QRestauranteEntity.restauranteEntity;
    final QMesaEntity mesa = QMesaEntity.mesaEntity;
    final QReservaEntity reserva = QReservaEntity.reservaEntity;


    @Override
    public List<MesaDto> getMesasDisponiveis(Long restauranteId, Integer numPessoas, LocalDate data) {
        var query = new JPAQuery<MesaDto>(em);

        query.select(Projections.constructor(MesaDto.class, mesa))
                .distinct()
                .from(restaurante)
                .join(restaurante.mesas, mesa)
                .join(mesa.reservas, reserva)
                .where(restaurante.id.eq(restauranteId)
                        .and(mesa.capacidadePessoas.goe(numPessoas))
                        .and(reserva.dataReserva.eq(data).not().or(reserva.status.eq(StatusReservaEnum.CANCELADA))));

        return query.fetch();
    }
}
