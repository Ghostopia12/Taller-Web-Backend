package com.nur.cuentas.controllers;


import com.nur.cuentas.entities.Deuda;
import com.nur.cuentas.entities.Gasto;
import com.nur.cuentas.services.implementations.GastoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/gastos")
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "*")public class GastoController {

    private final GastoServiceImpl gastoService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll(
            @RequestParam("role") int role){
        if (role != 0 && role != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<Gasto> gastos = gastoService.findAll();
        if (gastos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of("gastos", gastos));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Gasto> findById(@PathVariable Long id, @RequestParam("role") int role){
        if (role == 3 || role == 4) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Gasto gasto = gastoService.findById(id);
        if (gasto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gasto);
    }

    @PostMapping("/create")
    public ResponseEntity<Gasto> save(@RequestBody Gasto gasto, @RequestParam("role") int role){
        if (role != 0 && role != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Gasto gastoCreated = gastoService.save(gasto);
        if (gastoCreated == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(gastoCreated);
    }


    @GetMapping("/condominio/{condominio_id}")
    public ResponseEntity<Gasto> findByCondominio_id(@PathVariable Integer condominio_id, @RequestParam("role") int role){
        if (role == 3 || role == 4) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Optional<Gasto> gasto = gastoService.findByCondominioId(condominio_id);
        if (gasto.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gasto.get());
    }

}
