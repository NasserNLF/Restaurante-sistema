package com.triersistemas.restaurante.entity;

import com.triersistemas.restaurante.dto.FuncionarioDto;
import com.triersistemas.restaurante.enuns.CargoEnum;
import com.triersistemas.restaurante.enuns.StatusReservaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
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
        this.nome = funcionarioDto.getNome();
        this.sobrenome = funcionarioDto.getSobrenome();
        this.cpf = funcionarioDto.getCpf();
        this.dataNascimento = validaIdade(funcionarioDto.getDataNascimento());
        this.sexo = funcionarioDto.getSexo();
        this.telefone = funcionarioDto.getTelefone();
        this.id = funcionarioDto.getId();
        this.cargo = funcionarioDto.getCargo();
        this.dataAdmissao = funcionarioDto.getDataAdmissao();
        this.salario = (this.cargo.compareTo(CargoEnum.FREELANCER) != 0) ? validaSalario(funcionarioDto.getSalario()) : funcionarioDto.getSalario();
        this.cargaHoraria = validaCargaHoraria(funcionarioDto.getCargaHoraria());
        this.restaurante = restauranteEntity;
    }

    public FuncionarioEntity putRegistro(FuncionarioDto funcionarioDto, RestauranteEntity restauranteEntity) {
        this.nome = funcionarioDto.getNome();
        this.sobrenome = funcionarioDto.getSobrenome();
        this.dataNascimento = validaIdade(funcionarioDto.getDataNascimento());
        this.sexo = funcionarioDto.getSexo();
        this.telefone = funcionarioDto.getTelefone();
        this.cargo = funcionarioDto.getCargo();
        this.dataAdmissao = funcionarioDto.getDataAdmissao();
        this.salario = validaSalario(funcionarioDto.getSalario());
        this.cargaHoraria = validaCargaHoraria(funcionarioDto.getCargaHoraria());
        this.restaurante = restauranteEntity;

        return this;
    }

    private Integer validaCargaHoraria(Integer horas) {
        if (horas > 220) {
            throw new IllegalArgumentException("ERRO: Carga horária acima do limite de 220 horas");
        }
        return horas;
    }

    private LocalDate validaIdade(LocalDate dataNascimento) {
        Period periodo = Period.between(LocalDate.now(), dataNascimento);
        if (periodo.getYears() > 100 || periodo.getYears() < 12) {
            throw new IllegalArgumentException("ERRO: A pessoa não pdoe ter mais de 100 anos e menos de 12");
        }

        return dataNascimento;
    }

    private BigDecimal validaSalario(BigDecimal salario) {
        if (salario.compareTo(BigDecimal.valueOf(1500)) == -1) {
            throw new IllegalArgumentException("ERRO: O funcionário deve receber mais que um salário minímo");
        }
        return salario;
    }


}
