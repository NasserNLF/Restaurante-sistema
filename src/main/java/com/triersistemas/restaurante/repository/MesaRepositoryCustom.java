package com.triersistemas.restaurante.repository;

import com.triersistemas.restaurante.dto.MesaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface MesaRepositoryCustom {
    Page<MesaDto> getMesasDisponiveis(Pageable pageable, Long restauranteId, Integer numPessoas, LocalDate data);
}
