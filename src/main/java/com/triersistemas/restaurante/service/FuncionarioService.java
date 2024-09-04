package com.triersistemas.restaurante.service;

import com.triersistemas.restaurante.dto.FuncionarioDto;
import com.triersistemas.restaurante.entity.FuncionarioEntity;

import java.util.List;

public interface FuncionarioService {
    FuncionarioDto postFuncionario(FuncionarioDto funcionarioDto);
    List<FuncionarioDto> getAllFuncionarios();
    FuncionarioDto getFuncionario(Long id);
    FuncionarioEntity getFuncionarioEntity(Long id);
    FuncionarioDto putFuncionario(Long id, FuncionarioDto funcionarioDto);
}
