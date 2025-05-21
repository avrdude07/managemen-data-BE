package com.example.auth_project.service;

import com.example.auth_project.model.entity.Employee;
import com.example.auth_project.model.request.LoginRequest;
import com.example.auth_project.model.request.RegisterRequest;
import com.example.auth_project.model.entity.Role;
import com.example.auth_project.model.entity.User;
import com.example.auth_project.repository.EmployeeRepository;
import com.example.auth_project.repository.RoleRepository;
import com.example.auth_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    public User register(RegisterRequest registerRequest){
        // Check is username and email already exist in database
        int countUsername = userRepository.countByUsername(registerRequest.getUsername());
        int countEmail = userRepository.countByEmail(registerRequest.getEmail());

        if(countUsername > 0){
            throw new RuntimeException("Username is already taken.");
        }

        if(countEmail > 0){
            throw new RuntimeException("Email is already taken");
        }

        // Convert DTO to entity
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated_at(new Date());
        user.setUpdated_at(new Date());

        // Fetch roles from the database
        Role userRole = roleRepository.findByRoleName("user")
                .orElseThrow(() -> new RuntimeException("Role not found."));
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));

        userRepository.save(user);

        // Automatically create new employee
        Employee employee = new Employee();
        employee.setUser(user);
        employee.setCreated_at(new Date());
        employee.setUpdated_at(new Date());
        employeeRepository.save(employee);

        return user;
    }


}
