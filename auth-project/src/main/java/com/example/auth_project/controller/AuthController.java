package com.example.auth_project.controller;

import com.example.auth_project.model.UserPrincipal;
import com.example.auth_project.model.request.LoginRequest;
import com.example.auth_project.model.request.RegisterRequest;
import com.example.auth_project.model.entity.Role;
import com.example.auth_project.model.entity.User;
import com.example.auth_project.repository.RoleRepository;
import com.example.auth_project.service.AuthService;
import com.example.auth_project.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest){
        try{
            User savedUser = authService.register(registerRequest);

            // Remove password for response
            savedUser.setPassword(null);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Register successfully");
            response.put("user", savedUser);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest){
        try{
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

            Authentication authentication = authManager.authenticate(authToken);
            UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

            String token = jwtService.generateToken(user.getUsername());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successfully");
            response.put("accessToken", token);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
