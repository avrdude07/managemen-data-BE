package com.example.auth_project.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateEmployeeRequest {
    private String empName;
    private String empEmail;
    private String empRole;
    private String empPhone;
    private Date dateOfJoin;
    private Long managerId;
}
