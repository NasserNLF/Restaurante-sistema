package com.triersistemas.restaurante.dto;


import com.triersistemas.restaurante.entity.FuncionarioEntity;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import com.triersistemas.restaurante.enuns.CargoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FuncionarioDto extends PessoaDto {
    private Long id;

    private CargoEnum cargo;

    private LocalDate dataAdmissao;
    private BigDecimal salario;
    private Integer cargaHoraria;

    private Long restauranteId;

    public FuncionarioDto(FuncionarioEntity funcionarioEntity) {
        this.nome = funcionarioEntity.getNome();
        this.sobrenome = funcionarioEntity.getSobrenome();
        this.cpf = funcionarioEntity.getCpf();
        this.dataNascimento = funcionarioEntity.getDataNascimento();
        this.sexo = funcionarioEntity.getSexo();
        this.telefone = funcionarioEntity.getTelefone();
        this.id = funcionarioEntity.getId();
        this.cargo = funcionarioEntity.getCargo();
        this.dataAdmissao = funcionarioEntity.getDataAdmissao();
        this.salario = funcionarioEntity.getSalario();
        this.cargaHoraria = funcionarioEntity.getCargaHoraria();
        this.restauranteId = funcionarioEntity.getRestaurante().getId();

    }
}
