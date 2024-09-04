package com.triersistemas.restaurante.service.impl;

import com.triersistemas.restaurante.dto.ClienteDto;
import com.triersistemas.restaurante.entity.ClienteEntity;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import com.triersistemas.restaurante.repository.ClienteRepository;
import com.triersistemas.restaurante.service.ClienteService;
import com.triersistemas.restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Override
    public ClienteDto postCliente(ClienteDto clienteDto) {
        var restaurante = getRestaurante(clienteDto.getRestauranteId());

        var cliente = new ClienteEntity(clienteDto, restaurante);

        return new ClienteDto(clienteRepository.save(cliente));
    }

    @Override
    public List<ClienteDto> getAllClientes() {
        return clienteRepository.findAll().stream().map(ClienteDto::new).toList();
    }

    @Override
    public ClienteDto getCliente(Long id) {
        var clienteEntity = getClienteEntity(id);

        return new ClienteDto(clienteEntity);
    }

    @Override
    public ClienteDto putCliente(Long id, ClienteDto clienteDto) {
        var clienteEntity = getClienteEntity(id);
        var restauranteEntity = getRestaurante(clienteDto.getRestauranteId());

        clienteEntity.putRegistro(clienteDto, restauranteEntity);
        clienteRepository.save(clienteEntity);

        return new ClienteDto(clienteEntity);
    }

    @Override
    public ClienteEntity getClienteEntity(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ERRO: O Cliente não existe"));
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public RestauranteEntity getRestaurante(Long id) {
        return restauranteService.getRestauranteEntity(id);
    }


}
