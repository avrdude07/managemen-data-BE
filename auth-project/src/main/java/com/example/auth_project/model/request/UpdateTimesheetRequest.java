package com.example.auth_project.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTimesheetRequest {
    @NotNull
    private String CheckerStatus;

    private String CheckerRemark;
}
