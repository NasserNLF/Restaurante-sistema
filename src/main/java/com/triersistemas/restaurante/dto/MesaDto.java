package com.triersistemas.restaurante.dto;

import com.triersistemas.restaurante.entity.MesaEntity;
import com.triersistemas.restaurante.entity.ReservaEntity;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MesaDto {
    private Long id;

    private Integer numero;

    private Integer capacidadePessoas;

    private Long idRestaurante;

    public MesaDto(MesaEntity mesaEntity) {
        this.id = mesaEntity.getId();
        this.numero = mesaEntity.getNumero();
        this.capacidadePessoas = mesaEntity.getCapacidadePessoas();
        this.idRestaurante = mesaEntity.getRestaurante().getId();
    }
}
