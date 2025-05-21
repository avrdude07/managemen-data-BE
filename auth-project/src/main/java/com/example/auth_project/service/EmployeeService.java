package com.example.auth_project.service;

import com.example.auth_project.model.entity.Employee;
import com.example.auth_project.model.projection.EmployeeProjection;
import com.example.auth_project.model.projection.EmployeeSummary;
import com.example.auth_project.model.request.UpdateEmployeeRequest;
import com.example.auth_project.model.response.EmployeeResponse;
import com.example.auth_project.repository.EmployeeRepository;
import com.example.auth_project.utils.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeResponse getEmployeeByUsername(String username){
        Employee employee = employeeRepository.findByUserUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found."));


        return EmployeeMapper.toResponse(employee);
    }

    public EmployeeResponse editEmployee(String username, UpdateEmployeeRequest employeeRequest){
        Employee employee = employeeRepository.findByUserUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found."));

        employee.setEmpName(employeeRequest.getEmpName());
        employee.setEmpEmail(employeeRequest.getEmpEmail());
        employee.setEmpPhone(employeeRequest.getEmpPhone());
        employee.setEmpRole(employeeRequest.getEmpRole());
        employee.setDateOfJoin(employeeRequest.getDateOfJoin());
        employee.setUpdated_at(new Date());

        if(employeeRequest.getManagerId() != null){
            Employee manager = employeeRepository.findById(employeeRequest.getManagerId())
                    .orElseThrow(() -> new UsernameNotFoundException("Manager not found."));

            employee.setManager(manager);
        } else {
            employee.setManager(null);
        }

        employeeRepository.save(employee);

        return EmployeeMapper.toResponse(employee);
    }

    public List<EmployeeResponse> findAllEmployee() {
        return employeeRepository.findAllByOrderByEmpIdAsc().stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

    public List<EmployeeSummary> findAllInIdAndName(){
        return employeeRepository.findAllProjectedByOrderByEmpIdAsc();
    }
}
