package com.fitnessapp.web;

import com.fitnessapp.exceptions.ReportDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ReportDataException.class)
    public ErrorResponse handleReportDataException(ReportDataException e) {

        return ErrorResponse.builder(e, HttpStatus.NOT_FOUND, e.getMessage())
                .title("Report Data Not Found")
                .property("timestamp", Instant.now())
                .property("errorCode", "REPORT_DATA_NOT_FOUND")
                .build();
    }
}
