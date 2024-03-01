package com.systemsltd.employeemanagement.exception;

import com.systemsltd.employeemanagement.dto.error.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionResponseHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorDto> handleServiceException(ServiceException ex) {

        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getCode());
        return ResponseEntity.internalServerError().body(errorDto);
    }

}
