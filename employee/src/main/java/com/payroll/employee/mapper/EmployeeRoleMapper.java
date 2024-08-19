package com.payroll.employee.mapper;

import com.payroll.employee.dto.EmployeeRoleDto;
import com.payroll.employee.entity.EmployeeRole;

public class EmployeeRoleMapper {

    public static EmployeeRoleDto mapToEmployeeRoleDto(EmployeeRole employeeRole,EmployeeRoleDto employeeRoleDto) {

        employeeRoleDto.setEmployeeId(employeeRole.getEmployeeId());
        employeeRoleDto.setRole(employeeRole.getRole());
        return employeeRoleDto;
    }

    public static EmployeeRole mapToEmployeeRole(EmployeeRoleDto employeeRoleDto , EmployeeRole employeeRole) {

        employeeRole.setEmployeeId(employeeRoleDto.getEmployeeId());
        employeeRole.setRole(employeeRoleDto.getRole());
        return employeeRole;
    }


}
