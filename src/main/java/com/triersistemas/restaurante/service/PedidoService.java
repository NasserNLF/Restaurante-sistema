package com.triersistemas.restaurante.service;

import com.triersistemas.restaurante.dto.PedidoDto;
import com.triersistemas.restaurante.entity.PedidoEntity;

import java.util.List;

public interface PedidoService {
    PedidoDto postPedido(PedidoDto pedidoDto);

    List<PedidoDto> getAllPedidos();

    PedidoDto getPedido(Long id);

    PedidoEntity getPedidoEntity(Long id);

    PedidoDto putPedido(Long id, PedidoDto pedidoDto);

    void deletePedido(Long id);


}
