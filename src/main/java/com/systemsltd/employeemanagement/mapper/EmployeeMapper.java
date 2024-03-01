package com.systemsltd.employeemanagement.mapper;

import com.systemsltd.employeemanagement.dto.response.EmployeeResponse;
import com.systemsltd.employeemanagement.entity.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeResponse toDTO(Employee employee);

    List<EmployeeResponse> toDTOsList(List<Employee> employees);
}
