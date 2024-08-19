package com.payroll.report.mapper;

import com.payroll.report.dto.SalaryDto;
import com.payroll.report.entity.Salary;

public class SalaryMapper {
    public static Salary mapToSalary(SalaryDto salaryDto, Salary salary)
    {
        salary.setBaseSalary(salaryDto.getBaseSalary());
        salary.setBenefits(salaryDto.getBenefits());
        salary.setHra(salaryDto.getHra());
        salary.setEmployeeId(salaryDto.getEmployeeId());
        return salary;

    }
    public static SalaryDto mapToSalaryDto(Salary salary, SalaryDto salaryDto)
    {
        salaryDto.setBaseSalary(salary.getBaseSalary());
        salaryDto.setBenefits(salary.getBenefits());
        salaryDto.setHra(salary.getHra());
        salaryDto.setEmployeeId(salary.getEmployeeId());
        return salaryDto;

    }
}
