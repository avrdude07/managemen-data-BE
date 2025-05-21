package com.example.auth_project.utils.mapper;

import com.example.auth_project.model.entity.Employee;
import com.example.auth_project.model.response.EmployeeResponse;

public class EmployeeMapper {

    public static EmployeeResponse toResponse(Employee employee) {
        if (employee == null) return null;

        EmployeeResponse response = new EmployeeResponse();
        response.setEmpId(employee.getEmpId());
        response.setEmpName(employee.getEmpName());
        response.setEmpEmail(employee.getEmpEmail());
        response.setEmpPhone(employee.getEmpPhone());
        response.setEmpRole(employee.getEmpRole());
        response.setDateOfJoin(employee.getDateOfJoin());
        response.setCreated_at(employee.getCreated_at());
        response.setUpdated_at(employee.getUpdated_at());
        response.setUserId(employee.getUser().getId());

        if (employee.getManager() != null) {
            EmployeeResponse.ManagerResponse managerDto = new EmployeeResponse.ManagerResponse();
            managerDto.setEmpId(employee.getManager().getEmpId());
            managerDto.setEmpName(employee.getManager().getEmpName());
            response.setManager(managerDto);
        }

        return response;
    }
}

