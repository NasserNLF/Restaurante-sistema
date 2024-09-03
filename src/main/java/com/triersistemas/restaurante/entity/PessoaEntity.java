package com.triersistemas.restaurante.entity;

import com.triersistemas.restaurante.enuns.SexoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;

@MappedSuperclass
abstract class PessoaEntity {

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String sobrenome;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.ORDINAL)
    private SexoEnum sexo;
}
