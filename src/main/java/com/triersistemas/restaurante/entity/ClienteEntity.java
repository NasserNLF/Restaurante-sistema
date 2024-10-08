package com.triersistemas.restaurante.entity;

import com.triersistemas.restaurante.dto.ClienteDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "cliente")
public class ClienteEntity extends PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    @Column(nullable = false)
    private Boolean flgBloqueado;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private RestauranteEntity restaurante;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<ReservaEntity> reservas = new ArrayList<>();

    public ClienteEntity(ClienteDto clienteDto, RestauranteEntity restauranteEntity) {
        this.nome = clienteDto.getNome();
        this.sobrenome = clienteDto.getSobrenome();
        this.cpf = clienteDto.getCpf();
        this.dataNascimento = clienteDto.getDataNascimento();
        this.sexo = clienteDto.getSexo();
        this.telefone = clienteDto.getTelefone();
        this.id = clienteDto.getId();
        this.dataCadastro = LocalDate.now();
        this.flgBloqueado = (clienteDto.getFlgBloqueado() != null) ? clienteDto.getFlgBloqueado() : false;
        this.restaurante = restauranteEntity;
    }

    public ClienteEntity putRegistro() {
        this.flgBloqueado = true;

        return this;

    }


}
