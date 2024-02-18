package com.nur.usuarios.controllers;

import com.nur.usuarios.entities.User;
import com.nur.usuarios.services.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Validated
@Tag(name = "Authentication controller")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private RestTemplate restTemplate;

    @PostMapping("/register")

    public ResponseEntity<User> register(
            @Valid @RequestBody User request)
    {
    return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> saveUser(@Valid @RequestBody User request) {
        User savedUser = authenticationService.register(request);
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user_id", savedUser.getId());
        requestBody.put("rol_id", 1);// savedUser.getRole()
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toString(), headers);
//        String otherBackendUrl = "http://localhost:7777/api/users-rol/";
//        ResponseEntity<String> response = restTemplate.postForEntity(otherBackendUrl, requestEntity, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        String otherBackendUrl
                = "http://localhost:7777/api/users-rol/";
        ResponseEntity<?> response = restTemplate
                .exchange(otherBackendUrl, HttpMethod.POST, requestEntity, Object.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<User> authenticate(
            @RequestBody User request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
