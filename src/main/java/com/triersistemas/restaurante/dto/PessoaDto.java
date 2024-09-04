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
    private String nome;
    private String sobrenome;
    private String cpf;
    private LocalDate dataNascimento;
    private SexoEnum sexo;
    private String telefone;
}
