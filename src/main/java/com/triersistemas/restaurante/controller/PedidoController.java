package com.triersistemas.restaurante.controller;

import com.triersistemas.restaurante.dto.MesaDto;
import com.triersistemas.restaurante.dto.PedidoDto;
import com.triersistemas.restaurante.dto.PedidoMaiorClienteDto;
import com.triersistemas.restaurante.enuns.StatusReservaEnum;
import com.triersistemas.restaurante.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("pedido-controller/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    //POST
    @PostMapping
    public ResponseEntity<?> postPedido(@RequestBody PedidoDto pedidoDto) {
        try {
            return ResponseEntity.ok(pedidoService.postPedido(pedidoDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //GETTER
    @GetMapping
    public List<PedidoDto> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPedido(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pedidoService.getPedido(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> putPedido(@PathVariable Long id, @RequestBody PedidoDto pedidoDto) {
        try {
            return ResponseEntity.ok(pedidoService.putPedido(id, pedidoDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
    }

    @GetMapping("/maiores-pedidos-cliente")
    public Page<PedidoMaiorClienteDto> findMaiorPedidoByCliente(@RequestParam(required = false, defaultValue = "10") Integer size,
                                                                @RequestParam(required = false, defaultValue = "0") Integer page,
                                                                @RequestParam Long idRestaurante) {
        return pedidoService.findMaiorPedidoByCliente(Pageable.ofSize(size).withPage(page), idRestaurante);

    }

    @GetMapping("/busca-pedido-param")
    Page<PedidoDto> findPedido(@RequestParam(defaultValue = "10", required = false) Integer size,
                               @RequestParam(defaultValue = "0", required = false) Integer page,
                               @RequestParam Long idRestaurante,
                               @RequestParam(required = false) LocalDate data,
                               @RequestParam(required = false) BigDecimal valor,
                               @RequestParam(required = false) StatusReservaEnum status,
                               @RequestParam(required = false) Long idCliente
    ) {
        return pedidoService.findPedido(Pageable.ofSize(size).withPage(page), idRestaurante, data, valor, status, idCliente);
    }

    @GetMapping("/pedidos-page")
    public Page<PedidoDto> findAllPedidosPage(@RequestParam(defaultValue = "10", required = false) Integer size,
                                              @RequestParam(defaultValue = "0", required = false) Integer page,
                                              @RequestParam Long idRestaurante,
                                              @RequestParam String descricao

    ) {
        return pedidoService.findAllPedidosPage(Pageable.ofSize(size).withPage(page), descricao, idRestaurante);
    }

}
