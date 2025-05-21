package com.example.auth_project.model.request;

import lombok.Data;

@Data
public class UpdateTimesheetByMakerRequest {
    private String name;
    private Long leadId;
    private String location;
    private String remarks;
    private Long checkerId;
}
