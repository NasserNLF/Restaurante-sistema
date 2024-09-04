package com.triersistemas.restaurante.service;

import com.triersistemas.restaurante.dto.MesaDto;
import com.triersistemas.restaurante.entity.MesaEntity;

import java.time.LocalDate;
import java.util.List;

public interface MesaService {
    MesaDto postMesa(MesaDto mesaDto);

    List<MesaDto> getAllMesas();

    MesaDto getMesa(Long id);

    MesaEntity getMesaEntity(Long id);

    MesaDto putMesa(Long id, MesaDto mesaDto);

    void deleteMesa(Long id);

    List<MesaDto> getMesasDisponiveisByDataByQtdPessoas(LocalDate data, Integer qtdPessoas, Long idRestaurante);
}
