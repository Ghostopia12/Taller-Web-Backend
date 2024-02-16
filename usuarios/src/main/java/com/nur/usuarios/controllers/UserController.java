package com.nur.usuarios.controllers;

import com.nur.usuarios.entities.User;
import com.nur.usuarios.services.implementations.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User controller", description = "Algun dia ramón va a colocar documentacion válida")

public class UserController {
    private PasswordEncoder passwordEncoder;
    private RestTemplate restTemplate;


    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> saveUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userService.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity = new HttpEntity<>(savedUser, headers);
        String otherBackendUrl = "http://localhost:7777/api/users-rol/";
        ResponseEntity<String> response = restTemplate.postForEntity(otherBackendUrl, requestEntity, String.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "true") Boolean enabled){
        Page<User> users = userService.findAll(page, size, enabled);
        Map<String, Object> response = Map.of(
                "users", users.getContent(),
                "currentPage", users.getNumber(),
                "totalItems", users.getTotalElements(),
                "totalPages", users.getTotalPages()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User updatedUserData) {
        User existingUser = userService.findById(id);

        if (existingUser != null) {
            existingUser.setUsername(updatedUserData.getUsername());
            existingUser.setPassword(passwordEncoder.encode(updatedUserData.getPassword()));
            existingUser.setEmail(updatedUserData.getEmail());
            User updatedUser = userService.editUser(existingUser);
            return ResponseEntity.ok(updatedUser);
        }

        return ResponseEntity.notFound().build();
    }

}
