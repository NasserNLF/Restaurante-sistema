package com.triersistemas.restaurante.service.impl;

import com.triersistemas.restaurante.dto.FuncionarioDto;
import com.triersistemas.restaurante.entity.FuncionarioEntity;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import com.triersistemas.restaurante.repository.FuncionarioRepository;
import com.triersistemas.restaurante.service.FuncionarioService;
import com.triersistemas.restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private RestauranteService restauranteService;


    @Override
    public FuncionarioDto postFuncionario(FuncionarioDto funcionarioDto) {
        var restaurante = getRestaurante(funcionarioDto.getRestauranteId());
        var funcionarioEntity = funcionarioRepository.save(new FuncionarioEntity(funcionarioDto, restaurante));

        return new FuncionarioDto(funcionarioEntity);
    }

    @Override
    public List<FuncionarioDto> getAllFuncionarios() {
        return funcionarioRepository.findAll().stream().map(FuncionarioDto::new).toList();
    }

    @Override
    public FuncionarioDto getFuncionario(Long id) {
        return new FuncionarioDto(getFuncionarioEntity(id));
    }

    @Override
    public FuncionarioEntity getFuncionarioEntity(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ERRO: Funcionário não existe"));
    }

    @Override
    public FuncionarioDto putFuncionario(Long id, FuncionarioDto funcionarioDto) {
        var restaurante = getRestaurante(funcionarioDto.getRestauranteId());
        var funcionarioEntity = getFuncionarioEntity(id);

        funcionarioEntity.putRegistro(funcionarioDto, restaurante);

        return new FuncionarioDto(funcionarioRepository.save(funcionarioEntity));
    }

    public RestauranteEntity getRestaurante(Long id){
        return restauranteService.getRestauranteEntity(id);
    }


}
