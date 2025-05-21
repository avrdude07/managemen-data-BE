package com.example.auth_project.model.response;

import com.example.auth_project.model.dto.SimpleEmployeeDTO;
import com.example.auth_project.model.entity.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class TimesheetResponse {
    private Long id;

    private String pName;

    private SimpleEmployeeDTO pLead;

    private String location;

    private String remarks;

    private SimpleEmployeeDTO maker;

    private SimpleEmployeeDTO checker;

    private String CheckerStatus;

    private String CheckerRemark;

    private Date CheckerSubmitDate;

    private Date SubmitDate;

}

