package com.triersistemas.restaurante.entity;


import com.triersistemas.restaurante.dto.RestauranteDto;
import com.triersistemas.restaurante.enuns.TipoComidaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "restaurante")
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String cnpj;

    private Integer estrelas;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private TipoComidaEnum tipoComida;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<ClienteEntity> clientes;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<FuncionarioEntity> funcionarios;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<MesaEntity> mesas;

    public RestauranteEntity(RestauranteDto restauranteDto) {
        this.id = restauranteDto.getId();
        this.nome = restauranteDto.getNome();
        this.cnpj = restauranteDto.getCnpj();
        this.estrelas = restauranteDto.getEstrelas();
        this.tipoComida = restauranteDto.getTipoComida();
    }

    public RestauranteEntity putRegistro(RestauranteDto atualizacao) {
        this.nome = atualizacao.getNome();
        this.estrelas = atualizacao.getEstrelas();
        this.tipoComida = atualizacao.getTipoComida();

        return this;
    }
}
