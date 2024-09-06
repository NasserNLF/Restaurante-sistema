package com.triersistemas.restaurante.service;

import com.triersistemas.restaurante.dto.PedidoMaiorClienteDto;
import com.triersistemas.restaurante.dto.ReservaDto;
import com.triersistemas.restaurante.dto.ReservaVlrTotalDto;
import com.triersistemas.restaurante.entity.ReservaEntity;
import com.triersistemas.restaurante.enuns.StatusReservaEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservaService {
    ReservaDto postReserva(ReservaDto reservaDto);

    List<ReservaDto> getAllReservas();

    ReservaDto getReserva(Long id);

    ReservaEntity getReservaEntity(Long id);

    ReservaDto putReservaStatus(Long id, StatusReservaEnum status);

    void deleteReserva(Long id);

    ReservaVlrTotalDto findVlrTotalByReserva(Long idRestaurante, Long idReserva);

    Page<ReservaDto> findByObservacao(Pageable pageable, Long idRestaurante, String descricao);

}
