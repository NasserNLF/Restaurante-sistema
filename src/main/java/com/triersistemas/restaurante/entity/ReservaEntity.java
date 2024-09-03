package com.triersistemas.restaurante.entity;

import com.triersistemas.restaurante.enuns.StatusReservaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.web.JsonPath;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "reserva")
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private MesaEntity mesa;

    @Column(nullable = false)
    private LocalDate dataReserva;

    @Column(nullable = false)
    private Integer quantidadePessoa;

    @Enumerated(EnumType.ORDINAL)
    private StatusReservaEnum status;

    private Integer avaliacao;

    private String observacao;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<PedidoEntity> pedidos;


}
