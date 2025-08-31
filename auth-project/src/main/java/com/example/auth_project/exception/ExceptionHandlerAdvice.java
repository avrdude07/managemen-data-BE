package com.example.auth_project.exception;

import com.example.auth_project.model.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(GeneralException.class)
    ResponseEntity<ErrorResponseDto> handleGeneralException(GeneralException generalException) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(generalException.getError());
        errorResponseDto.setStatus(HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponseDto> handleOtherException(Exception e) {
        log.error("Internal Server Error ", e);
        StackTraceElement[] stackTrace = e.getStackTrace();
        getErrorLocation(stackTrace);

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(e.getMessage());
        errorResponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

    private void getErrorLocation(StackTraceElement[] stackTrace) {
        for (StackTraceElement element : stackTrace) {
            String className = element.getClassName();
            if (className.startsWith("id.co.app")) {
                String errorLocation = String.format("%s.%s(%s:%d)",
                        element.getClassName(),
                        element.getMethodName(),
                        element.getFileName(),
                        element.getLineNumber());
                log.error(errorLocation);
            }
        }
    }
}
