package com.example.auth_project.controller;

import com.example.auth_project.model.entity.Timesheet;
import com.example.auth_project.model.request.CreateTimesheetRequest;
import com.example.auth_project.model.request.UpdateTimesheetByMakerRequest;
import com.example.auth_project.model.request.UpdateTimesheetRequest;
import com.example.auth_project.model.response.EmployeeResponse;
import com.example.auth_project.model.response.TimesheetResponse;
import com.example.auth_project.service.TimesheetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/timesheet")
public class TimesheetController {

    @Autowired
    private TimesheetService timesheetService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTimesheet(Authentication authentication){
        try{
            String username = authentication.getName();
            List<TimesheetResponse> timesheets = timesheetService.getAllTimesheet(username);

            Map<String, Object> response = new HashMap<>();
            response.put("timesheetList", timesheets);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/checker/all")
    public ResponseEntity<?> getAllTimesheetByChecker(Authentication authentication){
        try{
            String username = authentication.getName();
            List<TimesheetResponse> timesheets = timesheetService.getAllTimesheetByChecker(username);

            Map<String, Object> response = new HashMap<>();
            response.put("timesheetList", timesheets);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTimesheet(@PathVariable("id") Long id){
        try{
            TimesheetResponse timesheet = timesheetService.getTimesheetById(id);

            Map<String, Object> response = new HashMap<>();
            response.put("timesheet", timesheet);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTimesheet(Authentication authentication, @RequestBody CreateTimesheetRequest request){
        try{
            String username = authentication.getName();
            Timesheet timesheet = timesheetService.createTimesheet(username, request);

            Map<String, Object> response = new HashMap<>();
            response.put("timesheet", timesheet);
            response.put("message", "Timesheet created successfully.");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("checker/edit/{id}")
    public ResponseEntity<?> editCheckerTimesheet(Authentication authentication, @RequestBody @Valid UpdateTimesheetRequest request, @PathVariable("id") Long id){
        try{
            String username = authentication.getName();
            Timesheet timesheet = timesheetService.editTimeSheetByChecker(username, request, id);

            Map<String, Object> response = new HashMap<>();
            response.put("timesheet", timesheet);
            response.put("message", "Timesheet updated successfully.");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("maker/edit/{id}")
    public ResponseEntity<?> editMakerTimesheet(Authentication authentication, @RequestBody @Valid UpdateTimesheetByMakerRequest request, @PathVariable("id") Long id){
        try{
            String username = authentication.getName();
            Timesheet timesheet = timesheetService.editTimesheetByMaker(username, request, id);

            Map<String, Object> response = new HashMap<>();
            response.put("timesheet", timesheet);
            response.put("message", "Timesheet updated successfully.");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTimesheet(Authentication authentication, @PathVariable("id") Long id){
        try{
            String username = authentication.getName();
            timesheetService.deleteTimesheetByMaker(username, id);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Timesheet deleted successfully.");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
