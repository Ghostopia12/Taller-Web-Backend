package com.nur.cuentas.controllers;


import com.nur.cuentas.entities.Deuda;
import com.nur.cuentas.services.implementations.DeudaServiceImpl;
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
@RequestMapping("/api/deudas")
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "*")public class DeudaController {

    private final DeudaServiceImpl deudaService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll(
            @RequestParam("role") int role){
        if (role != 0 && role != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<Deuda> deudas = deudaService.findAll();
        if (deudas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of("deudas", deudas));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Deuda> findById(@PathVariable Long id, @RequestParam("role") int role){
        if (role == 3 || role == 4) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Deuda deuda = deudaService.findById(id);
        if (deuda == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deuda);
    }

    @PostMapping("/create")
    public ResponseEntity<Deuda> save(@RequestBody Deuda deuda, @RequestParam("role") int role){
        if (role != 0 && role != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Deuda deudaCreated = deudaService.save(deuda);
        if (deudaCreated == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(deudaCreated);
    }


    @GetMapping("/residencia/{residencia_id}")
    public ResponseEntity<Deuda> findByResidenciaId(@PathVariable Integer residencia_id, @RequestParam("role") int role){
        if (role == 3 || role == 4) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Optional<Deuda> deuda = deudaService.findByResidenciaId(residencia_id);
        if (deuda.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deuda.get());
    }

}
