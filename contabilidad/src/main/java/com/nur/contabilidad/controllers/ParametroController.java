package com.nur.contabilidad.controllers;


import com.nur.contabilidad.entities.Parametro;
import com.nur.contabilidad.services.implementations.ParametroServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/parametros")
@Validated
@Tag(name = "Parametro controller")
@RequiredArgsConstructor
public class ParametroController {

    private final ParametroServiceImpl parametroService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "true") Boolean enabled){
        Page<Parametro> parametros = parametroService.findAll(page, size, enabled);
        Map<String, Object> response = Map.of(
                "parametros", parametros.getContent(),
                "currentPage", parametros.getNumber(),
                "totalItems", parametros.getTotalElements(),
                "totalPages", parametros.getTotalPages()
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Parametro> findById(@PathVariable Long id){
        Parametro parametro = parametroService.findById(id);
        if (parametro == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(parametro);
    }

    @PostMapping("/create")
    public ResponseEntity<Parametro> save(@RequestBody Parametro parametro){
        Parametro parametroCreated = parametroService.save(parametro);
        if (parametroCreated == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(parametroCreated);
    }


}
