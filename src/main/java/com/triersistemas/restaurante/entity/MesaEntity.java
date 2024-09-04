package com.triersistemas.restaurante.entity;


import com.triersistemas.restaurante.dto.MesaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "mesa")
public class MesaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private Integer capacidadePessoas;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private RestauranteEntity restaurante;

    @OneToMany(mappedBy = "mesa", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<ReservaEntity> reservas;

    public MesaEntity(MesaDto mesaDto, RestauranteEntity restauranteEntity) {
        this.id = mesaDto.getId();
        this.numero = mesaDto.getNumero();
        this.capacidadePessoas = mesaDto.getCapacidadePessoas();
        this.restaurante = restauranteEntity;
    }

    public MesaEntity putRegistro(MesaDto mesaDto, RestauranteEntity restauranteEntity){
        this.numero = mesaDto.getNumero();
        this.capacidadePessoas = mesaDto.getCapacidadePessoas();
        this.restaurante = restauranteEntity;

        return this;
    }

}
