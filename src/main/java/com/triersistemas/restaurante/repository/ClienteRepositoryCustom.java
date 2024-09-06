package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.dto.ClienteReservasValores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteRepositoryCustom {
    Page<ClienteReservasValores> findReservasAndValoresByCliente(Pageable pageable, Long idRestaurante);
}
