package com.nur.usuarios.controllers;

import com.nur.usuarios.entities.User;
import com.nur.usuarios.services.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Validated
@Tag(name = "Authentication controller")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")

    public ResponseEntity<User> register(
            @Valid @RequestBody User request)
    {
    return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<User> authenticate(
            @RequestBody User request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
