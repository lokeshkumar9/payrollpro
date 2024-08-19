package com.payroll.employee.entity;

import com.payroll.employee.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="EmployeeRole")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRole {
    @Id
    @GeneratedValue()
    private Long employeeRoleId;

    @NotNull(message = "Employee ID cannot be null")
    @Column(name = "employee_id")
    private Long employeeId;

    @NotNull(message = "Role cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public EmployeeRole(Long employeeId, Role role) {
        setEmployeeId(employeeId);
        setRole(role);
    }

}
