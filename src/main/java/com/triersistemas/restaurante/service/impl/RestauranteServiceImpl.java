package com.triersistemas.restaurante.service.impl;

import com.triersistemas.restaurante.dto.RestauranteDto;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import com.triersistemas.restaurante.repository.RestauranteRepository;
import com.triersistemas.restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public RestauranteDto postRestaurante(RestauranteDto restauranteDto) {
        var restauranteEntity = restauranteRepository.save(new RestauranteEntity(restauranteDto));

        return new RestauranteDto(restauranteEntity);
    }

    @Override
    public List<RestauranteDto> getAllRestaurantes() {
        return restauranteRepository.findAll().stream().map(RestauranteDto::new).toList();
    }

    @Override
    public RestauranteDto getRestaurante(Long id) {
        return new RestauranteDto(getRestauranteEntity(id));
    }

    @Override
    public RestauranteDto putRestaurante(Long id, RestauranteDto restauranteDto) {
        var restauranteEntity = getRestauranteEntity(id);

        restauranteRepository.save(restauranteEntity.putRegistro(restauranteDto));

        return new RestauranteDto(restauranteEntity);
    }

    @Override
    public RestauranteEntity getRestauranteEntity(Long id) {
        var restauranteEntity = restauranteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ERRO: Não existe nenhum restaurante com esse ID"));

        return restauranteEntity;
    }
}
