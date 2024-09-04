package com.triersistemas.restaurante.dto;

import com.triersistemas.restaurante.entity.ClienteEntity;
import com.triersistemas.restaurante.entity.MesaEntity;
import com.triersistemas.restaurante.entity.PedidoEntity;
import com.triersistemas.restaurante.entity.ReservaEntity;
import com.triersistemas.restaurante.enuns.StatusReservaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservaDto {
    private Long id;
    private Long clienteId;
    private Long mesaId;
    private LocalDate dataReserva;
    private Integer quantidadePessoas;
    private StatusReservaEnum status;
    private String observacao;

    public ReservaDto(ReservaEntity reservaEntity) {
        this.id = reservaEntity.getId();
        this.clienteId = reservaEntity.getCliente().getId();
        this.mesaId = reservaEntity.getMesa().getId();
        this.dataReserva = reservaEntity.getDataReserva();
        this.quantidadePessoas = reservaEntity.getQuantidadePessoas();
        this.status = reservaEntity.getStatus();
        this.observacao = reservaEntity.getObservacao();
    }
}
