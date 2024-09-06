package com.triersistemas.restaurante.controller;

import com.triersistemas.restaurante.dto.PedidoDto;
import com.triersistemas.restaurante.dto.ReservaDto;
import com.triersistemas.restaurante.dto.ReservaVlrTotalDto;
import com.triersistemas.restaurante.enuns.StatusReservaEnum;
import com.triersistemas.restaurante.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva-controller/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    //POST

    @PostMapping
    public ResponseEntity<?> postReserva(@RequestBody ReservaDto reservaDto) {
        try {
            return ResponseEntity.ok(reservaService.postReserva(reservaDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //GET

    @GetMapping
    public List<ReservaDto> getAllReservas() {
        return reservaService.getAllReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReserva(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reservaService.getReserva(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> putReservaStatus(@PathVariable Long id, @RequestParam StatusReservaEnum status) {
        try {
            return ResponseEntity.ok(reservaService.putReservaStatus(id, status));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
    }

    @GetMapping("/valorTotal")
    public ReservaVlrTotalDto getVlrTotalReserva(@RequestParam Long idRestaurante, @RequestParam Long idReserva) {
        return reservaService.findVlrTotalByReserva(idRestaurante, idReserva);
    }

    @GetMapping("/observacao")
    Page<ReservaDto> findByObservacao(@RequestParam(defaultValue = "10", required = false) Integer size,
                                      @RequestParam(defaultValue = "0", required = false) Integer page,
                                      @RequestParam Long idRestaurante,
                                      @RequestParam String observacao) {
        return reservaService.findByObservacao(Pageable.ofSize(size).withPage(page), idRestaurante, observacao);
    }

}
