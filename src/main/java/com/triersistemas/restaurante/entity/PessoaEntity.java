package com.triersistemas.restaurante.entity;

import com.triersistemas.restaurante.enuns.SexoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@MappedSuperclass
abstract class PessoaEntity {

    @Column(nullable = false)
    protected String nome;
    @Column(nullable = false)
    protected String sobrenome;
    @Column(nullable = false, unique = true)
    protected String cpf;
    @Column(nullable = false)
    protected LocalDate dataNascimento;
    @Enumerated(EnumType.ORDINAL)
    protected SexoEnum sexo;
    @Column(nullable = false)
    protected String telefone;

}
