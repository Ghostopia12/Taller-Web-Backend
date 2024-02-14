package com.nur.contabilidad.controllers;


import com.nur.contabilidad.entities.Pago;
import com.nur.contabilidad.services.implementations.PagoServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pagos")
@Validated
@Tag(name = "Pago controller")
@RequiredArgsConstructor
public class PagoController {

    private final PagoServiceImpl pagoService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "true") Boolean enabled){
        Page<Pago> pagos = pagoService.findAll(page, size, enabled);
        Map<String, Object> response = Map.of(
                "pagos", pagos.getContent(),
                "currentPage", pagos.getNumber(),
                "totalItems", pagos.getTotalElements(),
                "totalPages", pagos.getTotalPages()
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pago> findById(@PathVariable Long id){
        Pago pago = pagoService.findById(id);
        if (pago == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pago);
    }

    @PostMapping("/create")
    public ResponseEntity<Pago> save(@RequestBody Pago pago){
        Pago pagoCreated = pagoService.save(pago);
        if (pagoCreated == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(pagoCreated);
    }


}
