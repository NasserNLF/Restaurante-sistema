package com.triersistemas.restaurante.service.impl;

import com.triersistemas.restaurante.dto.ReservaDto;
import com.triersistemas.restaurante.entity.ClienteEntity;
import com.triersistemas.restaurante.entity.MesaEntity;
import com.triersistemas.restaurante.entity.ReservaEntity;
import com.triersistemas.restaurante.repository.ReservaRepository;
import com.triersistemas.restaurante.service.ClienteService;
import com.triersistemas.restaurante.service.MesaService;
import com.triersistemas.restaurante.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MesaService mesaService;


    @Override
    public ReservaDto postReserva(ReservaDto reservaDto) {
        var cliente = getCliente(reservaDto.getClienteId());
        var mesa = getMesa(reservaDto.getMesaId());

        var reservaEntity = reservaRepository.save(new ReservaEntity(reservaDto, cliente, mesa));

        return new ReservaDto(reservaEntity);
    }

    @Override
    public List<ReservaDto> getAllReservas() {
        return reservaRepository.findAll().stream().map(ReservaDto::new).toList();
    }

    @Override
    public ReservaDto getReserva(Long id) {
        return new ReservaDto(getReservaEntity(id));
    }

    @Override
    public ReservaEntity getReservaEntity(Long id) {
        return reservaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ERRO: Reserva não existe"));
    }

    @Override
    public ReservaDto putReserva(Long id, ReservaDto reservaDto) {
        var cliente = getCliente(reservaDto.getClienteId());
        var mesa = getMesa(reservaDto.getMesaId());
        var reservaEntity = getReservaEntity(id);

        reservaEntity.putRegistro(reservaDto, cliente, mesa);

        return new ReservaDto(reservaRepository.save(reservaEntity));
    }

    public ClienteEntity getCliente(Long id){
        return clienteService.getClienteEntity(id);
    }

    public MesaEntity getMesa(Long id){
        return mesaService.getMesaEntity(id);
    }






}
