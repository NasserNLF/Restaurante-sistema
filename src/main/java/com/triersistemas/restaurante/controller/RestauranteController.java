package com.triersistemas.restaurante.controller;

import com.triersistemas.restaurante.dto.RestauranteDto;
import com.triersistemas.restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante-controller")
public class RestauranteController {

    @Autowired
    public RestauranteService restauranteService;

    @PostMapping("/restaurante")
    public RestauranteDto postRestaurante(@RequestBody RestauranteDto restauranteDto) {
        return restauranteService.postRestaurante(restauranteDto);
    }

    @GetMapping("/restaurante")
    public List<RestauranteDto> getAllRestaurantes() {
        return restauranteService.getAllRestaurantes();
    }

    @GetMapping("/restaurante/{id}")
    public ResponseEntity<?> getRestaurante(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(restauranteService.getRestaurante(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/restaurante/{id}")
    public ResponseEntity<?> putRestaurante(@PathVariable Long id, @RequestBody RestauranteDto restauranteDto) {
        try {
            return ResponseEntity.ok(restauranteService.putRestaurante(id, restauranteDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
