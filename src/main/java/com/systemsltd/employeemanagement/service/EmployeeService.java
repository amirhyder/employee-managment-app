package com.systemsltd.employeemanagement.service;

import com.systemsltd.employeemanagement.dto.request.CreateEmployeeRequest;
import com.systemsltd.employeemanagement.dto.request.UpdateEmployeeRequest;
import com.systemsltd.employeemanagement.dto.response.EmployeeResponse;
import com.systemsltd.employeemanagement.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(CreateEmployeeRequest createEmployeeRequest);
    void updateEmployeeStatus(UpdateEmployeeRequest updateEmployeeRequest);

    Page<Employee> getEmployees(Pageable pageable);

    List<EmployeeResponse> getManagerEmployees();
}
