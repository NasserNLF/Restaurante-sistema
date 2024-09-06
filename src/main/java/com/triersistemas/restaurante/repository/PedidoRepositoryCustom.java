package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.dto.PedidoDto;
import com.triersistemas.restaurante.dto.PedidoMaiorClienteDto;
import com.triersistemas.restaurante.enuns.StatusReservaEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PedidoRepositoryCustom {
    Page<PedidoMaiorClienteDto> findMaiorPedidoByCliente(Pageable pageable, Long idRestaurante);

    Page<PedidoDto> findPedido(Pageable pageable, Long idRestaurante, LocalDate date, BigDecimal valor, StatusReservaEnum status, Long idCliente);

    Page<PedidoDto> findAllPedidosPage(Pageable pageable, String descricao, Long idRestaurante);

}
