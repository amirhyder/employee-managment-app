package com.systemsltd.employeemanagement.controller;

import com.systemsltd.employeemanagement.dto.request.CreateEmployeeRequest;
import com.systemsltd.employeemanagement.dto.request.UpdateEmployeeRequest;
import com.systemsltd.employeemanagement.dto.response.EmployeeResponse;
import com.systemsltd.employeemanagement.entity.Employee;
import com.systemsltd.employeemanagement.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest){
        EmployeeResponse employeeResponse= employeeService.createEmployee(createEmployeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponse);
    }

    @PutMapping
    public ResponseEntity<String> updateEmployeeStatus(@RequestBody UpdateEmployeeRequest updateEmployeeRequest){
        employeeService.updateEmployeeStatus(updateEmployeeRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Employee status updated successfully");
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getEmployees(Pageable pageable){
        Page<Employee> employeeResponses= employeeService.getEmployees(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponses);
    }

    @GetMapping("/manager")
    public ResponseEntity<List<EmployeeResponse>> getManagerEmployees(){
        List<EmployeeResponse> employeeResponses=employeeService.getManagerEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponses);
    }

}
