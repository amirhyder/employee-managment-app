package com.systemsltd.employeemanagement.service.impl;

import com.systemsltd.employeemanagement.constant.AppConstants;
import com.systemsltd.employeemanagement.constant.Error;
import com.systemsltd.employeemanagement.dto.request.CreateEmployeeRequest;
import com.systemsltd.employeemanagement.dto.request.UpdateEmployeeRequest;
import com.systemsltd.employeemanagement.dto.response.EmployeeResponse;
import com.systemsltd.employeemanagement.entity.Employee;
import com.systemsltd.employeemanagement.exception.ServiceException;
import com.systemsltd.employeemanagement.mapper.DepartmentMapper;
import com.systemsltd.employeemanagement.mapper.EmployeeMapper;
import com.systemsltd.employeemanagement.repository.EmployeeRepository;
import com.systemsltd.employeemanagement.service.DepartmentService;
import com.systemsltd.employeemanagement.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    private final EmployeeMapper employeeMapper;
    private final DepartmentMapper departmentMapper;
    @Override
    public EmployeeResponse createEmployee(CreateEmployeeRequest createEmployeeRequest) {
        departmentService.validateDepartmentId(createEmployeeRequest.getDepartment().getId());
        Employee employee=buildEmployeeEntity(createEmployeeRequest);
        return employeeMapper.toDTO(employeeRepository.save(employee));
    }

    @Override
    @Transactional
    public void updateEmployeeStatus(UpdateEmployeeRequest updateEmployeeRequest) {
        validateEmployeeId(updateEmployeeRequest.getId());
        validateStatus(updateEmployeeRequest.getStatus());
        employeeRepository.updateEmployeeStatus(updateEmployeeRequest.getId(),updateEmployeeRequest.getStatus());
    }

    private void validateStatus(String status) {
        if(!StringUtils.hasText(status)){
            throw new ServiceException(Error.STATUS_NOT_PROVIDED.getMessage(), Error.STATUS_NOT_PROVIDED.getCode());
        }
        if(!(status.equalsIgnoreCase(AppConstants.ACTIVE) || status.equalsIgnoreCase(AppConstants.INACTIVE))){
            throw new ServiceException(Error.STATUS_NOT_VALID.getMessage(), Error.STATUS_NOT_VALID.getCode());
        }
    }

    @Override
    public Page<Employee> getEmployees(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 50);
        Page<Employee> employees= employeeRepository.findAll(pageable);
        if(CollectionUtils.isEmpty(employees.getContent())){
            throw new ServiceException(Error.NO_EMPLOYEE_FOUND.getMessage(), Error.NO_EMPLOYEE_FOUND.getCode());
        }else{
            return employees;
        }
    }

    @Override
    public List<EmployeeResponse> getManagerEmployees() {
        List<Employee> employees=employeeRepository.getManagerEmployee(true);
        if(CollectionUtils.isEmpty(employees)){
            throw new ServiceException(Error.NO_MANAGER_FOUND.getMessage(), Error.NO_MANAGER_FOUND.getCode());
        }else{
            return employeeMapper.toDTOsList(employees);
        }

    }

    private void validateEmployeeId(Long id) {
        if(id !=null){
            employeeRepository.findById(id).orElseThrow(()-> new ServiceException(Error.EMPLOYEE_ID_NOT_FOUND.getMessage(), Error.EMPLOYEE_ID_NOT_FOUND.getCode()));
        }
        else{
            throw new ServiceException(Error.EMPLOYEE_ID_NOT_PROVIDED.getMessage(), Error.EMPLOYEE_ID_NOT_PROVIDED.getCode());
        }
    }

    private Employee buildEmployeeEntity(CreateEmployeeRequest createEmployeeRequest) {
        return Employee.builder()
                .name(createEmployeeRequest.getName())
                .status(createEmployeeRequest.getStatus())
                .isManager(createEmployeeRequest.getIsManager())
                .department(departmentMapper.toEntity(createEmployeeRequest.getDepartment()))
                .build();

    }

}
