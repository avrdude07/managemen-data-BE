package com.example.auth_project.model.response;


import com.example.auth_project.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeResponse {

    private Long empId;

    private String empName;

    private String empRole;

    private String empEmail;

    private Date dateOfJoin;

    private String empPhone;

    private Date created_at;

    private Date updated_at;

    private Long userId;

    private ManagerResponse manager;

    @Data
    public static class ManagerResponse{
        private Long empId;
        private String empName;
    }
}
