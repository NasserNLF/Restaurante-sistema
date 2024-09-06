package com.triersistemas.restaurante.service.impl;

import com.triersistemas.restaurante.dto.RestauranteDto;
import com.triersistemas.restaurante.dto.RestauranteFaturamentoDiaDto;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import com.triersistemas.restaurante.repository.RestauranteRepository;
import com.triersistemas.restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public RestauranteDto postRestaurante(RestauranteDto restauranteDto) {

        adequaCnpj(restauranteDto);

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

        adequaCnpj(restauranteDto);

        var restauranteEntity = getRestauranteEntity(id);

        restauranteRepository.save(restauranteEntity.putRegistro(restauranteDto));

        return new RestauranteDto(restauranteEntity);
    }

    @Override
    public void deleteRestaurante(Long id) {
        restauranteRepository.deleteById(id);
    }

    @Override
    public RestauranteFaturamentoDiaDto getFaturamentoDia(Long idRestaurante, LocalDate data) {
        return restauranteRepository.findFaturamentoByRestauranteIdAndData(idRestaurante, data);
    }

    @Override
    public RestauranteFaturamentoDiaDto getMaiorFaturamentoMes(Long idRestaurante, Integer mes) {
        return restauranteRepository.findMaiorFaturamentoMesByRestaurante(idRestaurante, mes);
    }

    @Override
    public Page<RestauranteDto> findAllRestaurantesPage(Pageable pageable, String nome) {
        return restauranteRepository.findAllRestaurantesPage(pageable, nome);
    }


    @Override
    public RestauranteEntity getRestauranteEntity(Long id) {
        var restauranteEntity = restauranteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ERRO: Não existe nenhum restaurante com esse ID"));

        return restauranteEntity;
    }

    //Validações
    public void adequaCnpj(RestauranteDto restauranteDto) {
        restauranteDto.setCnpj(restauranteDto.getCnpj().replaceAll("\\D", ""));

        if (restauranteDto.getCnpj().length() != 14) {
            throw new IllegalArgumentException("ERRO: O CNPJ precisa ter 14 números!");
        }
    }


}
