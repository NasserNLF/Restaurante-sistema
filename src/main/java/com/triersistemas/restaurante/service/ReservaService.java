package com.triersistemas.restaurante.service;

import com.triersistemas.restaurante.dto.ReservaDto;
import com.triersistemas.restaurante.entity.ReservaEntity;

import java.util.List;

public interface ReservaService {
    ReservaDto postReserva(ReservaDto reservaDto);
    List<ReservaDto> getAllReservas();
    ReservaDto getReserva(Long id);
    ReservaEntity getReservaEntity(Long id);
    ReservaDto putReserva(Long id, ReservaDto reservaDto);
}
