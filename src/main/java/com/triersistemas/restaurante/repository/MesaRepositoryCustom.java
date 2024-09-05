package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.dto.MesaDto;

import java.time.LocalDate;
import java.util.List;

public interface MesaRepositoryCustom {
    List<MesaDto> getMesasDisponiveis(Long restauranteId, Integer numPessoas, LocalDate data);
}
