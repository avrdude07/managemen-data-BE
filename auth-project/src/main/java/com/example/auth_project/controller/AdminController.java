package com.example.auth_project.controller;

import com.example.auth_project.model.response.AdminResponse;
import com.example.auth_project.model.response.TimesheetResponse;
import com.example.auth_project.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    private TimesheetService timesheetService;

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/timesheet/admin")
    public ResponseEntity<?> getAllTimesheetByChecker(){
        try{
            List<AdminResponse> timesheets = timesheetService.getAllTimesheetByAdmin();

            Map<String, Object> response = new HashMap<>();
            response.put("timesheetList", timesheets);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }}
