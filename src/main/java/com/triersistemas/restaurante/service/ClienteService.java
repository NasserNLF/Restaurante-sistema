package com.triersistemas.restaurante.service;

import com.triersistemas.restaurante.dto.ClienteDto;
import com.triersistemas.restaurante.entity.ClienteEntity;

import java.util.List;

public interface ClienteService {
    ClienteDto postCliente(ClienteDto clienteDto);

    List<ClienteDto> getAllClientes();

    ClienteDto getCliente(Long id);

    ClienteDto putCliente(Long id, ClienteDto clienteDto);

    ClienteEntity getClienteEntity(Long id);

    void deleteCliente(Long id);


}
