package com.systemsltd.employeemanagement.mapper;

import com.systemsltd.employeemanagement.dto.request.DepartmentRequest;
import com.systemsltd.employeemanagement.entity.Department;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department toEntity(DepartmentRequest department);
}
