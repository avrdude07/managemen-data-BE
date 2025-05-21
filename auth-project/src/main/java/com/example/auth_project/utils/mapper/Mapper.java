package com.example.auth_project.utils.mapper;

import com.example.auth_project.model.dto.SimpleEmployeeDTO;
import com.example.auth_project.model.entity.Employee;

public class Mapper {

    public static SimpleEmployeeDTO toSimple(Employee emp){
        return emp == null ? null : new SimpleEmployeeDTO(emp.getEmpId(), emp.getEmpName());
    }

}
