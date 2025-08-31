package com.example.auth_project.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class GeneralException extends RuntimeException implements Serializable {
    private final String error;

    public String getError() {
        return error;
    }
}
