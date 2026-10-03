package com.songsquare.api.auth;

import com.songsquare.api.auth.dto.AuthResponse;
import com.songsquare.api.auth.dto.LoginRequest;
import com.songsquare.api.auth.dto.RegisterRequest;
import com.songsquare.api.exceptions.AuthenticationException;
import com.songsquare.api.security.JwtTokenProvider;
import com.songsquare.api.user.UserEntity;
import com.songsquare.api.user.UserRepository;
import com.songsquare.util.ObjectComparator;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Transactional
    public AuthResponse register(RegisterRequest request) throws AuthenticationException{
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AuthenticationException("Username is already taken!");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AuthenticationException("Email is already registered!");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        String token = tokenProvider.generateToken(request.getUsername());

        return AuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(tokenProvider.getExpirationMs())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public AuthResponse login(LoginRequest request) throws AuthenticationException{

        if (request == null || ObjectComparator.isNullOrEmpty(request.getUsernameOrEmail(),true)
             || ObjectComparator.isNullOrEmpty(request.getPassword(),true) ) {
            throw new AuthenticationException("Please input a username or email and a password.");
        } 

        String token = tokenProvider.generateToken(request.getUsernameOrEmail());
        UserEntity user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail())
                .orElseThrow(() -> new AuthenticationException("User not found"));

        boolean isMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!isMatch) {
            throw new AuthenticationException("Wrong Password!");
        } 
        
        return AuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(tokenProvider.getExpirationMs())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}