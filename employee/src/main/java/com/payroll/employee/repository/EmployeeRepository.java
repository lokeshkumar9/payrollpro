package com.payroll.employee.repository;


import com.payroll.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Long> {
    Optional<Employee> findByEmployeeId(Long employeeId);
    Optional<Employee> findByPhoneNumber(String phoneNumber);
    Optional<List<Employee>> findAllByManagerId(Long managerId);
}


