package com.example.auth_project.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "timesheets")
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "project_name")
    private String pName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "project_lead", referencedColumnName = "employee_id")
    private Employee pLead;

    @NotNull
    @Column(name = "location")
    private String location;

    @Column(name = "remarks")
    private String remarks;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "maker", referencedColumnName = "employee_id")
    private Employee maker;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "checker", referencedColumnName = "employee_id")
    private Employee checker;

    @NotNull
    @Column(name = "checker_status")
    private String CheckerStatus;

    @Column(name = "checker_remark")
    private String CheckerRemark;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "submit_date")
    private Date SubmitDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "checker_submit_date")
    private Date CheckerSubmitDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "created_at", updatable = false)
    private Date created_at;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "updated_at")
    private Date updated_at;
}
