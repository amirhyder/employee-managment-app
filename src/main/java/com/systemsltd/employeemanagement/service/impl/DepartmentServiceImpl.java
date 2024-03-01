package com.systemsltd.employeemanagement.service.impl;

import com.systemsltd.employeemanagement.constant.Error;
import com.systemsltd.employeemanagement.exception.ServiceException;
import com.systemsltd.employeemanagement.repository.DepartmentRepository;
import com.systemsltd.employeemanagement.service.DepartmentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    @Override
    public void validateDepartmentId(Long id) {
        departmentRepository.findById(id).orElseThrow(()-> new ServiceException(Error.DEPARTMENT_ID_NOT_FOUND.getMessage(),Error.DEPARTMENT_ID_NOT_FOUND.getCode()));
    }
}
