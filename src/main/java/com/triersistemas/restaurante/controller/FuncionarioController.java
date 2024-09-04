package com.triersistemas.restaurante.controller;

import com.triersistemas.restaurante.dto.ClienteDto;
import com.triersistemas.restaurante.dto.FuncionarioDto;
import com.triersistemas.restaurante.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario-controller/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    //POST

    @PostMapping
    public ResponseEntity<?> postFuncionario(@RequestBody FuncionarioDto funcionarioDto) {
        try {
            return ResponseEntity.ok(funcionarioService.postFuncionario(funcionarioDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //GET

    @GetMapping
    public ResponseEntity<?> getAllFuncionarios() {
        try {
            return ResponseEntity.ok(funcionarioService.getAllFuncionarios());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFuncionario(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(funcionarioService.getFuncionario(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> putFuncionario(@PathVariable Long id, @RequestBody FuncionarioDto funcionarioDto) {
        try {
            return ResponseEntity.ok(funcionarioService.putFuncionario(id, funcionarioDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteFuncionario(@PathVariable Long id) {
        funcionarioService.deleteFuncionario(id);
    }


}
