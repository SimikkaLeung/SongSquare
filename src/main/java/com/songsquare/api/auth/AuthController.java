package com.songsquare.api.auth;

import com.songsquare.api.auth.dto.AuthResponse;
import com.songsquare.api.auth.dto.LoginRequest;
import com.songsquare.api.auth.dto.RegisterRequest;
import com.songsquare.api.exceptions.AuthenticationException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse authResponse = null;

        try {
            authResponse = authService.register(request);
        } catch (AuthenticationException  e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest request) {

        AuthResponse authResponse = null;

        try {
            authResponse = authService.login(request);
        } catch (AuthenticationException  e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

        return ResponseEntity.ok(authResponse);

    }
}