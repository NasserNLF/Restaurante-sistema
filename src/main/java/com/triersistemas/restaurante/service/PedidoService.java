package com.triersistemas.restaurante.service;

import com.triersistemas.restaurante.dto.PedidoDto;
import com.triersistemas.restaurante.dto.PedidoMaiorClienteDto;
import com.triersistemas.restaurante.entity.PedidoEntity;
import com.triersistemas.restaurante.enuns.StatusReservaEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PedidoService {
    PedidoDto postPedido(PedidoDto pedidoDto);

    List<PedidoDto> getAllPedidos();

    PedidoDto getPedido(Long id);

    PedidoEntity getPedidoEntity(Long id);

    PedidoDto putPedido(Long id, PedidoDto pedidoDto);

    void deletePedido(Long id);

    Page<PedidoMaiorClienteDto> findMaiorPedidoByCliente(Pageable pageable, Long idRestaurante);

    Page<PedidoDto> findPedido(Pageable pageable, Long idRestaurante, LocalDate date, BigDecimal valor, StatusReservaEnum status, Long idCliente);

    Page<PedidoDto> findAllPedidosPage(Pageable pageable, String descricao, Long idRestaurante);


}
