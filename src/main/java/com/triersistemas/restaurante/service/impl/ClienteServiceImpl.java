package com.triersistemas.restaurante.service.impl;

import com.triersistemas.restaurante.dto.ClienteDto;
import com.triersistemas.restaurante.dto.ClienteReservasValores;
import com.triersistemas.restaurante.entity.ClienteEntity;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import com.triersistemas.restaurante.repository.ClienteRepository;
import com.triersistemas.restaurante.service.ClienteService;
import com.triersistemas.restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Override
    public ClienteDto postCliente(ClienteDto clienteDto) {

        adequaCpf(clienteDto);
        validaIdade(clienteDto.getDataNascimento());

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
    public ClienteDto putCliente(Long id) {


        var clienteEntity = getClienteEntity(id);

        clienteEntity.putRegistro();
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

    @Override
    public Page<ClienteReservasValores> findReservasAndValoresByCliente(Pageable pageable, Long idRestaurante) {
        return clienteRepository.findReservasAndValoresByCliente(pageable, idRestaurante);
    }

    public RestauranteEntity getRestaurante(Long id) {
        return restauranteService.getRestauranteEntity(id);
    }


    // Validações
    private void validaIdade(LocalDate dataNascimento) {
        Period periodo = Period.between(dataNascimento, LocalDate.now());
        if (periodo.getYears() > 100 || periodo.getYears() < 12) {
            throw new IllegalArgumentException("ERRO: A pessoa não pdoe ter mais de 100 anos e menos de 12");
        }
    }

    private void adequaCpf(ClienteDto clienteDto) {

        clienteDto.setCpf(clienteDto.getCpf().replaceAll("\\D", ""));

        if (clienteDto.getCpf().length() != 11) {
            throw new IllegalArgumentException("ERRO: O CPF deve ter 11 números!");
        }
    }

}
