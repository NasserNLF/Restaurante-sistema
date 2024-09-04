package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.entity.ClienteEntity;
import com.triersistemas.restaurante.entity.ReservaEntity;
import com.triersistemas.restaurante.enuns.StatusReservaEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
    List<ReservaEntity> findAllByClienteAndStatusAndDataReservaBetween(ClienteEntity clienteEntity, StatusReservaEnum status, LocalDate dataAtual, LocalDate dataFim);

    List<ReservaEntity> findAllByClienteAndStatus(ClienteEntity clienteEntity, StatusReservaEnum status);
}
