package com.payroll.report.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="payroll")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payrollId;
    private Long employeeId;
    private Long baseSalary;
    private Long hra;
    private Long benefits;
    private Long netSalary;
    private Long deduction;
    private LocalDate localDateTime;
}
