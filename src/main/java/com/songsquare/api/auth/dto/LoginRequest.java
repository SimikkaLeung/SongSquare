package com.songsquare.api.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank (message = "Username or email cannot be empty!")
    private String usernameOrEmail;

    @NotBlank (message = "Password cannot be empty!")
    private String password;
}