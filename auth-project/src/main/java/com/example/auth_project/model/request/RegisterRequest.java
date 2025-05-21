package com.example.auth_project.model.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;

    private String username;

    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

}
