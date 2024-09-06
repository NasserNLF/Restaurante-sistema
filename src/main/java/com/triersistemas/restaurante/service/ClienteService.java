package com.triersistemas.restaurante.service;

import com.triersistemas.restaurante.dto.ClienteDto;
import com.triersistemas.restaurante.dto.ClienteReservasValores;
import com.triersistemas.restaurante.entity.ClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {
    ClienteDto postCliente(ClienteDto clienteDto);

    List<ClienteDto> getAllClientes();

    ClienteDto getCliente(Long id);

    ClienteDto putCliente(Long id);

    ClienteEntity getClienteEntity(Long id);

    void deleteCliente(Long id);

    Page<ClienteReservasValores> findReservasAndValoresByCliente(Pageable pageable, Long idRestaurante);

    /*
    TODO Falta implantar:
     Buscar clientes com mais reservas concluídas. X
     Buscar clientes com maior valor gasto. X
     */


}
