package com.triersistemas.restaurante.dto;


import com.triersistemas.restaurante.entity.FuncionarioEntity;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import com.triersistemas.restaurante.enuns.CargoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FuncionarioDto extends PessoaDto {
    private Long id;

    private CargoEnum cargo;

    private LocalDate dataAdmissao;
    private BigDecimal salario;
    private Integer cargaHoraria;

    private Long restauranteId;

    public FuncionarioDto(FuncionarioEntity funcionarioEntity) {
        super(funcionarioEntity.getNome(), funcionarioEntity.getSobrenome(), funcionarioEntity.getCpf(), funcionarioEntity.getDataNascimento(), funcionarioEntity.getSexo(), funcionarioEntity.getTelefone());
        this.id = funcionarioEntity.getId();
        this.cargo = funcionarioEntity.getCargo();
        this.dataAdmissao = funcionarioEntity.getDataAdmissao();
        this.salario = funcionarioEntity.getSalario();
        this.cargaHoraria = funcionarioEntity.getCargaHoraria();
        this.restauranteId = funcionarioEntity.getRestaurante().getId();

    }
}
