package com.triersistemas.restaurante.dto;

import com.triersistemas.restaurante.enuns.SexoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
abstract class PessoaDto {
    protected String nome;
    protected String sobrenome;
    protected String cpf;
    protected LocalDate dataNascimento;
    protected SexoEnum sexo;
    protected String telefone;
}
