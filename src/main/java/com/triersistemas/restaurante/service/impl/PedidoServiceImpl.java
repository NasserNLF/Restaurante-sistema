package com.triersistemas.restaurante.service.impl;

import com.triersistemas.restaurante.dto.PedidoDto;
import com.triersistemas.restaurante.dto.PedidoMaiorClienteDto;
import com.triersistemas.restaurante.entity.PedidoEntity;
import com.triersistemas.restaurante.entity.ReservaEntity;
import com.triersistemas.restaurante.enuns.StatusReservaEnum;
import com.triersistemas.restaurante.repository.PedidoRepository;
import com.triersistemas.restaurante.service.PedidoService;
import com.triersistemas.restaurante.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ReservaService reservaService;

    @Override
    public PedidoDto postPedido(PedidoDto pedidoDto) {
        var reservaEntity = getReserva(pedidoDto.getReservaId());

        validaReserva(reservaEntity);

        var pedidoEntity = pedidoRepository.save(new PedidoEntity(pedidoDto, reservaEntity));

        return new PedidoDto(pedidoEntity);
    }

    @Override
    public List<PedidoDto> getAllPedidos() {
        return pedidoRepository.findAll().stream().map(PedidoDto::new).toList();
    }

    @Override
    public PedidoDto getPedido(Long id) {
        return new PedidoDto(getPedidoEntity(id));
    }

    @Override
    public PedidoEntity getPedidoEntity(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ERRO: Pedido não existe"));
    }

    @Override
    public PedidoDto putPedido(Long id, PedidoDto pedidoDto) {
        var reserva = getReserva(pedidoDto.getReservaId());
        var pedidoEntity = getPedidoEntity(id);

        return new PedidoDto(pedidoRepository.save(pedidoEntity.putRegistro(pedidoDto, reserva)));
    }

    @Override
    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public Page<PedidoMaiorClienteDto> findMaiorPedidoByCliente(Pageable pageable, Long idRestaurante) {
        return pedidoRepository.findMaiorPedidoByCliente(pageable, idRestaurante);
    }

    @Override
    public Page<PedidoDto> findPedido(Pageable pageable, Long idRestaurante, LocalDate date, BigDecimal valor, StatusReservaEnum status, Long idCliente) {
        return pedidoRepository.findPedido(pageable, idRestaurante, date, valor, status, idCliente);
    }

    @Override
    public Page<PedidoDto> findAllPedidosPage(Pageable pageable, String descricao, Long idRestaurante) {
        return pedidoRepository.findAllPedidosPage(pageable, descricao, idRestaurante);
    }

    public ReservaEntity getReserva(Long id) {
        return reservaService.getReservaEntity(id);
    }

    private void validaReserva(ReservaEntity reservaEntity) {
        if (reservaEntity.getDataReserva().isBefore(LocalDate.now()) && reservaEntity.getStatus().compareTo(StatusReservaEnum.AGENDADA) != 0) {
            throw new IllegalArgumentException("ERRO: Você não pode inserir pedidos nessa reserva!");
        }
    }

}
