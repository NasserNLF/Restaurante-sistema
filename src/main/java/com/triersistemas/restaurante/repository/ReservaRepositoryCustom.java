package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.dto.ReservaDto;
import com.triersistemas.restaurante.dto.ReservaVlrTotalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ReservaRepositoryCustom {
    ReservaVlrTotalDto findVlrTotalByReserva(Long idRestaurante, Long idReserva);

    Page<ReservaDto> findByObservacao(Pageable pageable, Long idRestaurante, String descricao);
}
