package com.example.auth_project.service;

import com.example.auth_project.model.entity.Employee;
import com.example.auth_project.model.entity.Timesheet;
import com.example.auth_project.model.request.CreateTimesheetRequest;
import com.example.auth_project.model.request.UpdateTimesheetByMakerRequest;
import com.example.auth_project.model.request.UpdateTimesheetRequest;
import com.example.auth_project.model.response.AdminResponse;
import com.example.auth_project.model.response.TimesheetResponse;
import com.example.auth_project.repository.EmployeeRepository;
import com.example.auth_project.repository.TimesheetRepository;
import com.example.auth_project.utils.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    public Timesheet createTimesheet(String username, CreateTimesheetRequest request){
        Employee maker = employeeRepo.findByUserUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found."));

        Employee pLead = employeeRepo.findById(request.getLeadId())
                .orElseThrow(() -> new UsernameNotFoundException("Employee leader not found."));
        Employee checker = employeeRepo.findById(request.getCheckerId())
                .orElseThrow(() -> new UsernameNotFoundException("Employee checker not found."));

        Timesheet timesheet = new Timesheet();
        timesheet.setPName(request.getName());
        timesheet.setPLead(pLead);
        timesheet.setMaker(maker);
        timesheet.setLocation(request.getLocation());
        timesheet.setRemarks(request.getRemarks());
        timesheet.setCheckerStatus("Pending");
        timesheet.setChecker(checker);
        timesheet.setCheckerRemark(null);
        timesheet.setSubmitDate(new Date());
        timesheet.setCreated_at(new Date());
        timesheet.setUpdated_at(new Date());

        timesheetRepo.save(timesheet);


        return timesheet;
    }

    public Timesheet editTimeSheetByChecker(String username, UpdateTimesheetRequest request, Long timesheetId){
        Employee checker = employeeRepo.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Employee not found."));

        Timesheet timesheet = timesheetRepo.findById(timesheetId)
                .orElseThrow(() -> new RuntimeException("Timesheet not found."));

        if(!Objects.equals(checker.getEmpId(), timesheet.getChecker().getEmpId())){
            throw new RuntimeException("You do not have permission to edit this data.");
        }

        timesheet.setCheckerRemark(request.getCheckerRemark());
        timesheet.setCheckerStatus(request.getCheckerStatus());
        timesheet.setCheckerSubmitDate(new Date());
        timesheet.setUpdated_at(new Date());

        timesheetRepo.save(timesheet);

        return timesheet;
    }

    public Timesheet editTimesheetByMaker(String username, UpdateTimesheetByMakerRequest request, Long timesheetId){
        Employee maker = employeeRepo.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Employee not found."));

        Timesheet timesheet = timesheetRepo.findById(timesheetId)
                .orElseThrow(() -> new RuntimeException("Timesheet not found."));

        if(request.getCheckerId() != null){
            Employee checker = employeeRepo.findById(request.getCheckerId())
                    .orElseThrow(() -> new UsernameNotFoundException("Employee checker not found."));
            timesheet.setChecker(checker);
        }

        if(request.getLeadId() != null){
            Employee pLead = employeeRepo.findById(request.getLeadId())
                    .orElseThrow(() -> new UsernameNotFoundException("Employee checker not found."));
            timesheet.setPLead(pLead);
        }

        if(!Objects.equals(maker.getEmpId(), timesheet.getMaker().getEmpId())){
            throw new RuntimeException("You do not have permission to edit this data.");
        }

        timesheet.setMaker(maker);
        timesheet.setPName(request.getName());
        timesheet.setRemarks(request.getRemarks());
        timesheet.setLocation(request.getLocation());
//        timesheet.setCheckerSubmitDate(new Date());
        timesheet.setUpdated_at(new Date());

        timesheetRepo.save(timesheet);
        return timesheet;
    }

    public TimesheetResponse getTimesheetById(Long id){
        Timesheet timesheet = timesheetRepo.findById(id).orElseThrow(() -> new RuntimeException("Timesheet not found."));
        TimesheetResponse response = new TimesheetResponse();

        Employee checker = employeeRepo.findById(timesheet.getChecker().getEmpId())
                .orElseThrow(() -> new UsernameNotFoundException("Employee checker not found."));
        timesheet.setChecker(checker);

        Employee pLead = employeeRepo.findById(timesheet.getPLead().getEmpId())
                .orElseThrow(() -> new UsernameNotFoundException("Employee checker not found."));
        timesheet.setPLead(pLead);

        response.setId(timesheet.getId());
        response.setPName(timesheet.getPName());
        response.setPLead(Mapper.toSimple(pLead));
        response.setLocation(timesheet.getLocation());
        response.setRemarks(timesheet.getRemarks());
        response.setSubmitDate(timesheet.getSubmitDate());
        response.setChecker(Mapper.toSimple(checker));
        return response;
    }

    public List<TimesheetResponse> getAllTimesheet(String username){
        Employee employee = employeeRepo.findByUserUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return timesheetRepo.findByMakerId(employee.getEmpId()).stream().map(ts -> {
            TimesheetResponse response = new TimesheetResponse();
            response.setId(ts.getId());
            response.setPName(ts.getPName());
            response.setPLead(Mapper.toSimple(ts.getPLead()));
            response.setLocation(ts.getLocation());
            response.setRemarks(ts.getRemarks());
            response.setMaker(Mapper.toSimple(ts.getMaker()));
            response.setSubmitDate(ts.getSubmitDate());
            response.setChecker(Mapper.toSimple(ts.getChecker()));
            response.setCheckerStatus(ts.getCheckerStatus());
            response.setCheckerRemark(ts.getCheckerRemark());
            response.setCheckerSubmitDate(ts.getCheckerSubmitDate());
            return response;
        }).toList();
    }

    public List<TimesheetResponse> getAllTimesheetByChecker(String username){
        Employee employee = employeeRepo.findByUserUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return timesheetRepo.findByCheckerId(employee.getEmpId()).stream().map(ts -> {
            TimesheetResponse response = new TimesheetResponse();
            response.setId(ts.getId());
            response.setPName(ts.getPName());
            response.setPLead(Mapper.toSimple(ts.getPLead()));
            response.setLocation(ts.getLocation());
            response.setRemarks(ts.getRemarks());
            response.setMaker(Mapper.toSimple(ts.getMaker()));
            response.setSubmitDate(ts.getSubmitDate());
            response.setChecker(Mapper.toSimple(ts.getChecker()));
            response.setCheckerStatus(ts.getCheckerStatus());
            response.setCheckerRemark(ts.getCheckerRemark());
            response.setCheckerSubmitDate(ts.getCheckerSubmitDate());
            return response;
        }).toList();
    }

    public void deleteTimesheetByMaker(String username, Long timesheetId){
        Employee maker = employeeRepo.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Employee not found."));

        Timesheet timesheet = timesheetRepo.findById(timesheetId)
                .orElseThrow(() -> new RuntimeException("Timesheet not found."));

        if(!Objects.equals(maker.getEmpId(), timesheet.getMaker().getEmpId())){
            throw new RuntimeException("You do not have permission to edit this data.");
        }

        timesheetRepo.delete(timesheet);
    }

    public List<AdminResponse> getAllTimesheetByAdmin(){


        return timesheetRepo.findAll().stream().map(ts -> {
            AdminResponse response = new AdminResponse();
            response.setMaker(ts.getMaker().getEmpName());
            response.setStatus(ts.getCheckerStatus());
            response.setChecker(ts.getChecker().getEmpName());
            response.setCheckerRemark(ts.getCheckerRemark());
            response.setSubmitDate(ts.getSubmitDate());
            return response;
        }).toList();
    }
}
