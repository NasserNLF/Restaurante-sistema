package com.triersistemas.restaurante.controller;

import com.triersistemas.restaurante.dto.FuncionarioDto;
import com.triersistemas.restaurante.dto.MesaDto;
import com.triersistemas.restaurante.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/mesa-controller/mesa")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    //POST
    @PostMapping
    public ResponseEntity<?> postMesa(@RequestBody MesaDto mesaDto) {
        try {
            return ResponseEntity.ok(mesaService.postMesa(mesaDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //GETTER
    @GetMapping
    public ResponseEntity<?> getAllMesa() {
        try {
            return ResponseEntity.ok(mesaService.getAllMesas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMesa(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(mesaService.getMesa(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/mesas-disponiveis")
    public List<MesaDto> getMesasDisponiveis(@RequestParam LocalDate data, @RequestParam Integer numPessoas, @RequestParam Long restauranteId) {
        return getMesasDisponiveis(data, numPessoas, restauranteId);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> putMesa(@PathVariable Long id, @RequestBody MesaDto mesaDto) {
        try {
            return ResponseEntity.ok(mesaService.putMesa(id, mesaDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteMesa(@PathVariable Long id) {
        mesaService.deleteMesa(id);
    }

    @GetMapping("/disponiveis")
    public Page<MesaDto> getMesasDisponiveis(@RequestParam(defaultValue = "10", required = false) Integer size,
                                             @RequestParam(defaultValue = "0", required = false) Integer page,
                                             @RequestParam Long restauranteId,
                                             @RequestParam Integer numPessoas,
                                             @RequestParam LocalDate data) {
        return mesaService.getMesasDisponiveis(Pageable.ofSize(size).withPage(page), restauranteId, numPessoas, data);
    }

}
