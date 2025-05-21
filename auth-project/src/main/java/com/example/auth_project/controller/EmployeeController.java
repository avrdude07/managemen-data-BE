package com.example.auth_project.controller;

import com.example.auth_project.model.entity.Employee;
import com.example.auth_project.model.entity.User;
import com.example.auth_project.model.projection.EmployeeSummary;
import com.example.auth_project.model.request.UpdateEmployeeRequest;
import com.example.auth_project.model.response.EmployeeResponse;
import com.example.auth_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("me/employee")
    public ResponseEntity<?> getEmployee(Authentication authentication){
        try{
            String username = authentication.getName();
            EmployeeResponse employee = employeeService.getEmployeeByUsername(username);

            Map<String, Object> response = new HashMap<>();
            response.put("employee", employee);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }

    }

    @PutMapping("me/employee/edit")
    public ResponseEntity<?> updateEmployee(Authentication authentication, @RequestBody UpdateEmployeeRequest request){
        try{
            String username = authentication.getName();
            EmployeeResponse employee = employeeService.editEmployee(username, request);

            Map<String, Object> response = new HashMap<>();
            response.put("employee", employee);
            response.put("message", "Employee updated successfully.");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }

    }

    @GetMapping("/employee/all")
    public ResponseEntity<?> getAllEmployee(){
        try{
            List<EmployeeResponse> employeeList = employeeService.findAllEmployee();

            Map<String, Object> response = new HashMap<>();
            response.put("employee", employeeList);
            response.put("message", "Get all employee successfully.");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }

    }

    @GetMapping("/employee/all/summary")
    public ResponseEntity<?> getAllSummaryEmployee(){
        try{
            List<EmployeeSummary> employeeList = employeeService.findAllInIdAndName();

            Map<String, Object> response = new HashMap<>();
            response.put("employee", employeeList);
            response.put("message", "Get all employee successfully.");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }

    }}
