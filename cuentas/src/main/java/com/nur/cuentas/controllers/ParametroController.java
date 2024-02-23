package com.nur.cuentas.controllers;


import com.nur.cuentas.entities.Pago;
import com.nur.cuentas.entities.Parametro;
import com.nur.cuentas.services.implementations.ParametroServiceImpl;
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
@RequestMapping("/api/parametros")
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "*")public class ParametroController {

    private final ParametroServiceImpl parametroService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll(
            @RequestParam("role") int role){
        if (role != 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<Parametro> parametros = parametroService.findAll();
        if (parametros.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of("parametros", parametros));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Parametro> findById(@PathVariable Long id, @RequestParam("role") int role){
        if (role != 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Parametro parametro = parametroService.findById(id);
        if (parametro == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(parametro);
    }

    @PostMapping("/create")
    public ResponseEntity<Parametro> save(@RequestBody Parametro parametro, @RequestParam("role") int role){
        if (role != 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Parametro parametroCreated = parametroService.save(parametro);
        if (parametroCreated == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(parametroCreated);
    }


    @GetMapping("/activo/{activo}")
    public ResponseEntity<Parametro> findByActivo(@PathVariable Boolean activo, @RequestParam("role") int role){
        if (role != 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Optional<Parametro> parametro = parametroService.findByActivo(activo);
        if (parametro.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(parametro.get());
    }

//    @GetMapping("/tipo/{tipo}")
//    public ResponseEntity<Parametro> findByTipo(@PathVariable Integer tipo, @RequestParam("role") int role){
//        if (role != 0) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//        Optional<Parametro> parametro = parametroService.findByTipo(tipo);
//        if (parametro.isEmpty()){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(parametro.get());
//    }


}
