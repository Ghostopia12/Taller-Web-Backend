package com.nur.cuentas.controllers;


import com.nur.cuentas.entities.Gasto;
import com.nur.cuentas.entities.Pago;
import com.nur.cuentas.services.implementations.PagoServiceImpl;
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
@RequestMapping("/api/pagos")
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "*")public class PagoController {

    private final PagoServiceImpl pagoService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll(
            @RequestParam("role") int role){
        if (role != 0 && role != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<Pago> pagos = pagoService.findAll();
        if (pagos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of("pagos", pagos));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pago> findById(@PathVariable Long id, @RequestParam("role") int role){
        if (role == 3 || role == 4) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Pago pago = pagoService.findById(id);
        if (pago == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pago);
    }

    @PostMapping("/create")
    public ResponseEntity<Pago> save(@RequestBody Pago pago, @RequestParam("role") int role){
        if (role == 3 || role == 4) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Pago pagoCreated = pagoService.save(pago);
        if (pagoCreated == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(pagoCreated);
    }

    @GetMapping("/deuda/{deuda_id}")
    public ResponseEntity<Pago> findByDeuda_id(@PathVariable Integer deuda_id, @RequestParam("role") int role){
        if (role == 3 || role == 4) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Optional<Pago> pago = pagoService.findByDeudaId(deuda_id);
        if (pago.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pago.get());
    }


    @GetMapping("/usuario/{usuario_id}")
    public ResponseEntity<Pago> findByUsuario_id(@PathVariable Integer usuario_id, @RequestParam("role") int role){
        if (role == 3 || role == 4) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Optional<Pago> pago = pagoService.findByUsuarioId(usuario_id);
        if (pago.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pago.get());
    }

    @GetMapping("/contable/{contable_id}")
    public ResponseEntity<Pago> findByContable_id(@PathVariable Integer contable_id, @RequestParam("role") int role){
        if (role != 0 && role != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Optional<Pago> pago = pagoService.findByContableId(contable_id);
        if (pago.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pago.get());
    }



}
