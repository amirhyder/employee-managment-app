package com.systemsltd.employeemanagement.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Error {

    DEPARTMENT_ID_NOT_FOUND("10001", "Department id not found"),
    EMPLOYEE_ID_NOT_FOUND("10002", "Employee id not found"),
    NO_EMPLOYEE_FOUND("10003", "No Employee found"),
    NO_MANAGER_FOUND("10004","No manager found"),
    EMPLOYEE_ID_NOT_PROVIDED("10005","Employee id is not provided for updating"),
    STATUS_NOT_PROVIDED("10006","Status is not provided for updating"),

    STATUS_NOT_VALID("10007","Status is not valid. Valid status are active or inactive");

    private final String code;
    private final String message;
}
