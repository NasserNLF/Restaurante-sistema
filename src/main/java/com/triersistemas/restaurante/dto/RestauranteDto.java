package com.triersistemas.restaurante.dto;

import com.triersistemas.restaurante.entity.*;
import com.triersistemas.restaurante.enuns.TipoComidaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestauranteDto {
    private Long id;
    private String nome;
    private String cnpj;
    private Integer estrelas;
    private TipoComidaEnum tipoComida;

    public RestauranteDto(RestauranteEntity restauranteEntity) {
        this.id = restauranteEntity.getId();
        this.nome = restauranteEntity.getNome();
        this.cnpj = restauranteEntity.getCnpj();
        this.estrelas = restauranteEntity.getEstrelas();
        this.tipoComida = restauranteEntity.getTipoComida();
    }


}


