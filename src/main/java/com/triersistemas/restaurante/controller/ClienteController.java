package com.triersistemas.restaurante.controller;

import com.triersistemas.restaurante.dto.ClienteDto;
import com.triersistemas.restaurante.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente-controller/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //POST

    @PostMapping
    public ResponseEntity<?> postCliente(@RequestBody ClienteDto clienteDto){
        try {
            return ResponseEntity.ok(clienteService.postCliente(clienteDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    //GETTER

    @GetMapping
    public ResponseEntity<?> getAllClientes(){
        try {
            return ResponseEntity.ok(clienteService.getAllClientes());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCliente(@PathVariable Long id){
        try {
            return ResponseEntity.ok(clienteService.getCliente(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //PUT

    @PutMapping("/{id}")
    public ResponseEntity<?> putCliente(@PathVariable Long id,@RequestBody ClienteDto clienteDto){
        try {
            return ResponseEntity.ok(clienteService.putCliente(id, clienteDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
