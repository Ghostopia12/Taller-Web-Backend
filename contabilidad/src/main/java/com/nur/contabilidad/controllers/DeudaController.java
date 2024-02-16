package com.nur.contabilidad.controllers;


import com.nur.contabilidad.entities.Deuda;
import com.nur.contabilidad.services.implementations.DeudaServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/deudas")
@Validated
@Tag(name = "Deuda controller")
@RequiredArgsConstructor
public class DeudaController {

    private final DeudaServiceImpl deudaService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "true") Boolean enabled){
        Page<Deuda> deudas = deudaService.findAll(page, size, enabled);
        Map<String, Object> response = Map.of(
                "deudas", deudas.getContent(),
                "currentPage", deudas.getNumber(),
                "totalItems", deudas.getTotalElements(),
                "totalPages", deudas.getTotalPages()
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Deuda> findById(@PathVariable Long id){
        Deuda deuda = deudaService.findById(id);
        if (deuda == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deuda);
    }

    @PostMapping("/create")
    public ResponseEntity<Deuda> save(@RequestBody Deuda deuda){
        Deuda deudaCreated = deudaService.save(deuda);
        if (deudaCreated == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(deudaCreated);
    }


}
