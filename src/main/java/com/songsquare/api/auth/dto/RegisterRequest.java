package com.songsquare.api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank (message = "Username cannot be empty!")
    @Size(min = 3, max = 30)
    private String username;

    @NotBlank
    @Email (message = "Email cannot be empty!")
    private String email;

    @NotBlank (message = "Password cannot be empty!")
    @Size(min = 6, max = 30)
    private String password;

    @Size(max = 50)
    private String displayName;

}