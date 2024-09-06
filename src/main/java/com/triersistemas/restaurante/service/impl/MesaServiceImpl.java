package com.triersistemas.restaurante.service.impl;

import com.triersistemas.restaurante.dto.MesaDto;
import com.triersistemas.restaurante.entity.MesaEntity;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import com.triersistemas.restaurante.repository.MesaRepository;
import com.triersistemas.restaurante.service.MesaService;
import com.triersistemas.restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MesaServiceImpl implements MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private RestauranteService restauranteService;


    @Override
    public MesaDto postMesa(MesaDto mesaDto) {
        var restaurante = getRestaurante(mesaDto.getIdRestaurante());

        verificaExistenciaNumMesa(mesaDto.getNumero(), restaurante);

        var mesaEntity = mesaRepository.save(new MesaEntity(mesaDto, restaurante));

        return new MesaDto(mesaEntity);
    }

    @Override
    public List<MesaDto> getAllMesas() {
        return mesaRepository.findAll().stream().map(MesaDto::new).toList();
    }

    @Override
    public MesaDto getMesa(Long id) {
        var mesa = getMesaEntity(id);

        return new MesaDto(mesa);
    }

    @Override
    public MesaEntity getMesaEntity(Long id) {
        return mesaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ERRO: Mesa não existe"));
    }


    @Override
    public MesaDto putMesa(Long id, MesaDto mesaDto) {
        var restauranteEntity = getRestaurante(mesaDto.getIdRestaurante());
        var mesaEntity = getMesaEntity(id);

        mesaEntity.putRegistro(mesaDto, restauranteEntity);

        return new MesaDto(mesaRepository.save(mesaEntity));
    }

    @Override
    public void deleteMesa(Long id) {
        mesaRepository.deleteById(id);
    }

    @Override
    public Page<MesaDto> getMesasDisponiveis(Pageable pageable, Long restauranteId, Integer numPessoas, LocalDate data) {
        return mesaRepository.getMesasDisponiveis(pageable, restauranteId, numPessoas, data);
    }

    public RestauranteEntity getRestaurante(Long id) {
        return restauranteService.getRestauranteEntity(id);
    }

    public void verificaExistenciaNumMesa(Integer numMesa, RestauranteEntity restauranteEntity) {
        if (mesaRepository.existsByNumeroAndRestaurante(numMesa, restauranteEntity)) {
            throw new IllegalArgumentException("ERRO: Já existe uma mesa com esse número no restaurante");
        }
    }


}
