package com.triersistemas.restaurante.service.impl;

import com.triersistemas.restaurante.dto.PedidoMaiorClienteDto;
import com.triersistemas.restaurante.dto.ReservaDto;
import com.triersistemas.restaurante.dto.ReservaVlrTotalDto;
import com.triersistemas.restaurante.entity.ClienteEntity;
import com.triersistemas.restaurante.entity.MesaEntity;
import com.triersistemas.restaurante.entity.ReservaEntity;
import com.triersistemas.restaurante.enuns.StatusReservaEnum;
import com.triersistemas.restaurante.repository.ReservaRepository;
import com.triersistemas.restaurante.service.ClienteService;
import com.triersistemas.restaurante.service.MesaService;
import com.triersistemas.restaurante.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        validaDataReserva(reservaDto.getDataReserva());

        var cliente = getCliente(reservaDto.getClienteId());

        validaQtdReservasCanceladasCliente(cliente);
        validaInadimplencia(cliente);

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
    public ReservaDto putReservaStatus(Long id, StatusReservaEnum status) {
        var reservaEntity = getReservaEntity(id);

        if (status.equals(StatusReservaEnum.CANCELADA)) {
            validaDataCancelamento(reservaEntity.getDataReserva());
        }

        if (status.equals(StatusReservaEnum.CONCLUIDA)) {
            validaDataConclusao(reservaEntity.getDataReserva());
        }

        reservaEntity.putRegistro(status);

        validaInadimplencia(reservaEntity.getCliente());

        return new ReservaDto(reservaRepository.save(reservaEntity));
    }

    @Override
    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public ReservaVlrTotalDto findVlrTotalByReserva(Long idRestaurante, Long idReserva) {
        return reservaRepository.findVlrTotalByReserva(idRestaurante, idReserva);
    }

    @Override
    public Page<ReservaDto> findByObservacao(Pageable pageable, Long idRestaurante, String descricao) {
        return reservaRepository.findByObservacao(pageable, idRestaurante, descricao);
    }


    public ClienteEntity getCliente(Long id) {
        return clienteService.getClienteEntity(id);
    }

    public MesaEntity getMesa(Long id) {
        return mesaService.getMesaEntity(id);
    }


    /*
    Validações
    1- Bloqueio de nova reserva, caso o cliente já cancelou 2 vezes no último mês
    2- Bloquear Cliente no caso de 3 inadimplências
     */

    //No momento do cadastro
    public void validaQtdReservasCanceladasCliente(ClienteEntity clienteEntity) {
        //Lista com as reservas feitas no último mês que foram canceladas pelo cliente
        var listaReservasCanceladas = reservaRepository.findAllByClienteAndStatusAndDataReservaBetween(clienteEntity, StatusReservaEnum.CANCELADA, LocalDate.now(), LocalDate.now().minusMonths(1));

        if (listaReservasCanceladas.size() >= 2) {
            throw new IllegalArgumentException("ERRO: O cliente já cancelou 2 reservas no último mês, logo ele deve esperar para reservar novamente");
        }
    }

    //No fim do PUT
    public void validaInadimplencia(ClienteEntity clienteEntity) {
        var listaReservasInadimplentes = reservaRepository.findAllByClienteAndStatus(clienteEntity, StatusReservaEnum.INADIMPLENTE);

        if (listaReservasInadimplentes.size() >= 3) {
            clienteEntity.setFlgBloqueado(true);
        }
    }

    //TODO PERGUNTAR SOBRE JUNÇÃO DOS MÉTODOS COM DATA

    //No momento do PUT
    public void validaDataCancelamento(LocalDate dataReserva) {
        if (dataReserva.isEqual(LocalDate.now()) || dataReserva.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERRO: Uma reserva só pode ser cancelada com pelo menos 1 dia de antecedência");
        }
    }

    //No momento do PUT
    public void validaDataConclusao(LocalDate dataReserva) {
        if (dataReserva.isEqual(LocalDate.now()) || dataReserva.isEqual(LocalDate.now().plusDays(1))) {
            throw new IllegalArgumentException("ERRO: Uma reserva só pode ser concluída no dia ou no dia posterior");
        }
    }

    public void validaDataReserva(LocalDate dataReserva) {
        if (dataReserva.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("ERRO: A data da reserva não pode ser feita no passado");
        }
    }


}
