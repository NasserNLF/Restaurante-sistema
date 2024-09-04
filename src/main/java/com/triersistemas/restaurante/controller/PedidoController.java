package com.triersistemas.restaurante.controller;

import com.triersistemas.restaurante.dto.MesaDto;
import com.triersistemas.restaurante.dto.PedidoDto;
import com.triersistemas.restaurante.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
