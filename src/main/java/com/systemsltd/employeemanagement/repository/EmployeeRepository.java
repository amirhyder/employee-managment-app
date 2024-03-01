package com.systemsltd.employeemanagement.repository;

import com.systemsltd.employeemanagement.dto.response.EmployeeResponse;
import com.systemsltd.employeemanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Modifying
    @Query("UPDATE employee e SET e.status = :status WHERE e.id = :id")
    void updateEmployeeStatus(@Param("id") Long id,@Param("status") String status);

    @Query(value = "SELECT * from employee e where e.is_Manager = :isManager ",nativeQuery = true)
    List<Employee> getManagerEmployee(@Param("isManager") Boolean isManager);
}
