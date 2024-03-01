package com.systemsltd.employeemanagement.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {
    private String errorMessage;
    private String errorCode;
}
