package com.payroll.employee.mapper;

import com.payroll.employee.dto.EmployeeDto;
import com.payroll.employee.entity.Employee;
import com.payroll.employee.entity.EmployeeRole;
import com.payroll.employee.enums.Role;
import com.payroll.employee.exception.ResourceNotFoundException;
import com.payroll.employee.repository.EmployeeRoleRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@AllArgsConstructor
public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee, EmployeeDto employeeDto){
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setJobTitle(employee.getJobTitle());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setManagerId(employee.getManagerId());
        employeeDto.setEmployeeRoles(new ArrayList<>());
        return employeeDto;
    }

    public static Employee mapToEmployee(EmployeeDto  employeeDto , Employee employee){
        employee.setEmployeeId(employeeDto.getEmployeeId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setJobTitle(employeeDto.getJobTitle());
        employee.setManagerId(employeeDto.getManagerId());
        employee.setDepartment(employeeDto.getDepartment());

        return employee;
    }

}
