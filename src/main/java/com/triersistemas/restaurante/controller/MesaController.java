package com.triersistemas.restaurante.controller;

import com.triersistemas.restaurante.dto.FuncionarioDto;
import com.triersistemas.restaurante.dto.MesaDto;
import com.triersistemas.restaurante.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
