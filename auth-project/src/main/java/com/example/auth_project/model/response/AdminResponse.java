package com.example.auth_project.model.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AdminResponse {
    private String maker;
    private String checker;
    private String status;
    private String checkerRemark;
    private Date submitDate;
}
