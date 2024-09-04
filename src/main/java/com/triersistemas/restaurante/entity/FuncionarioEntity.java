package com.triersistemas.restaurante.entity;

import com.triersistemas.restaurante.dto.FuncionarioDto;
import com.triersistemas.restaurante.enuns.CargoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Entity(name = "funcionario")
public class FuncionarioEntity extends PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private CargoEnum cargo;

    @Column(nullable = false)
    private LocalDate dataAdmissao;
    @Column(nullable = false)
    private BigDecimal salario;
    @Column(nullable = false)
    private Integer cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private RestauranteEntity restaurante;

    public FuncionarioEntity(FuncionarioDto funcionarioDto, RestauranteEntity restauranteEntity) {
        super(funcionarioDto.getNome(), funcionarioDto.getSobrenome(), funcionarioDto.getCpf(), funcionarioDto.getDataNascimento(), funcionarioDto.getSexo(), funcionarioDto.getTelefone());
        this.id = funcionarioDto.getId();
        this.cargo = funcionarioDto.getCargo();
        this.dataAdmissao = funcionarioDto.getDataAdmissao();
        this.salario = funcionarioDto.getSalario();
        this.cargaHoraria = funcionarioDto.getCargaHoraria();
        this.restaurante = restauranteEntity;
    }


}
