package com.example.auth_project.model.request;

import com.example.auth_project.model.entity.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class CreateTimesheetRequest {
    @NotNull
    private String name;

    @NotNull
    private Long leadId;

    @NotNull
    private String location;

    private String remarks;

    @NotNull
    private Long checkerId;
}
