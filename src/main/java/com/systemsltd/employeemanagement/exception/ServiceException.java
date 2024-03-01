package com.systemsltd.employeemanagement.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceException extends  RuntimeException{
    private String code;

    public ServiceException() {
        super();
    }
    public ServiceException(String message, String code) {
        super(message);
        this.code = code;
    }
}
