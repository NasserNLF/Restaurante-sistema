package com.triersistemas.restaurante.controller;

import com.triersistemas.restaurante.dto.RestauranteDto;
import com.triersistemas.restaurante.dto.RestauranteFaturamentoDiaDto;
import com.triersistemas.restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/restaurante-controller/restaurante")
public class RestauranteController {

    @Autowired
    public RestauranteService restauranteService;

    @PostMapping
    public ResponseEntity<?> postRestaurante(@RequestBody RestauranteDto restauranteDto) {
        try {
            return ResponseEntity.ok(restauranteService.postRestaurante(restauranteDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<RestauranteDto> getAllRestaurantes() {
        return restauranteService.getAllRestaurantes();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getRestaurante(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(restauranteService.getRestaurante(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> putRestaurante(@PathVariable Long id, @RequestBody RestauranteDto restauranteDto) {
        try {
            return ResponseEntity.ok(restauranteService.putRestaurante(id, restauranteDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteRestaurante(@PathVariable Long id) {
        restauranteService.deleteRestaurante(id);
    }

    @GetMapping("/faturamento")
    public RestauranteFaturamentoDiaDto getFaturamentoDia(@RequestParam Long id, @RequestParam LocalDate data) {
        return restauranteService.getFaturamentoDia(id, data);
    }

    @GetMapping("/maior-faturamento")
    public RestauranteFaturamentoDiaDto getMaiorFaturamento(@RequestParam Long id, @RequestParam Integer mes) {
        return restauranteService.getMaiorFaturamentoMes(id, mes);
    }

    @GetMapping("/page-all")
    public Page<RestauranteDto> findAllRestaurantesPage(@RequestParam(defaultValue = "10", required = false) Integer size,
                                                        @RequestParam(defaultValue = "0", required = false) Integer page,
                                                        @RequestParam(required = false) String nome) {
        return restauranteService.findAllRestaurantesPage(Pageable.ofSize(size).withPage(page), nome);
    }


}
