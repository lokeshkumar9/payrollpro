package com.payroll.employee.dto;

import com.payroll.employee.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRoleDto {
  private  long employeeId;
  private Role role;
}
