package com.example.auth_project.model.projection;

import com.example.auth_project.model.entity.Employee;

import java.util.Date;

public interface EmployeeProjection {
    Long getEmpId();
    String getEmpName();
    String getEmpRole();
    Date getDateOfJoin();
    String getEmpEmail();
    String getEmpPhone();
    Date getCreated_at();
    Date getUpdated_at();
    Long getUserId();
    Employee getManager();
}
