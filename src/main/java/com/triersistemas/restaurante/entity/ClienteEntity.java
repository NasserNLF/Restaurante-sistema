package com.triersistemas.restaurante.entity;

import com.triersistemas.restaurante.dto.ClienteDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Entity(name = "cliente")
public class ClienteEntity extends PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    private Integer quantidadeReservas;

    @Column(nullable = false)
    private BigDecimal quantidadeValorGasto;

    private Boolean flgBloqueado;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private RestauranteEntity restaurante;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<ReservaEntity> reservas;

    public ClienteEntity(ClienteDto clienteDto, RestauranteEntity restauranteEntity) {
        super(clienteDto.getNome(), clienteDto.getSobrenome(), clienteDto.getCpf(), clienteDto.getDataNascimento(), clienteDto.getSexo(), clienteDto.getTelefone());
        this.id = clienteDto.getId();
        this.dataCadastro = clienteDto.getDataCadastro();
        this.flgBloqueado = (clienteDto.getFlgBloqueado() != null) ? clienteDto.getFlgBloqueado() : false;
        this.restaurante = restauranteEntity;
    }

}
