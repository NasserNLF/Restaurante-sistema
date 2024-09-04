package com.triersistemas.restaurante.service;

import com.triersistemas.restaurante.dto.MesaDto;
import com.triersistemas.restaurante.entity.MesaEntity;

import java.util.List;

public interface MesaService {
    MesaDto postMesa(MesaDto mesaDto);
    List<MesaDto> getAllMesas();
    MesaDto getMesa(Long id);
    MesaEntity getMesaEntity(Long id);
    MesaDto putMesa(Long id, MesaDto mesaDto);
}
