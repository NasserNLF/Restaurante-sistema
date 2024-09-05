package com.triersistemas.restaurante.dto;

import com.triersistemas.restaurante.entity.ClienteEntity;
import com.triersistemas.restaurante.entity.ReservaEntity;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteDto extends PessoaDto {
    private Long id;

    private LocalDate dataCadastro;
    private Integer quantidadeReservas;
    private BigDecimal quantidadeValorGasto;
    private Boolean flgBloqueado;

    private Long restauranteId;

    public ClienteDto(ClienteEntity clienteEntity) {
        this.nome = clienteEntity.getNome();
        this.cpf = clienteEntity.getCpf();
        this.sobrenome = clienteEntity.getSobrenome();
        this.dataNascimento = clienteEntity.getDataNascimento();
        this.sexo = clienteEntity.getSexo();
        this.telefone = clienteEntity.getTelefone();
        this.id = clienteEntity.getId();
        this.dataCadastro = clienteEntity.getDataCadastro();
        this.quantidadeReservas = clienteEntity.getReservas().size();
        this.flgBloqueado = clienteEntity.getFlgBloqueado();
        this.restauranteId = clienteEntity.getRestaurante().getId();
    }
}
