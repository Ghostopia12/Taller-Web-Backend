package com.nur.usuarios.services;

import com.nur.usuarios.entities.User;
import com.nur.usuarios.enums.Role;
import com.nur.usuarios.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public User register(User request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(request);
    }

    public User authenticate(User request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = tokenService.generateToken(authentication, user);
        user.setToken(jwtToken);
        return user;

    }
}
