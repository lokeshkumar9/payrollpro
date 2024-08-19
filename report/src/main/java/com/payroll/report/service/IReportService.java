package com.payroll.report.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.payroll.report.dto.ReportDto;
import com.payroll.report.dto.SalaryDto;
import org.springframework.http.ResponseEntity;


public interface IReportService {

    boolean deleteAccount(Long employeeId);

    boolean updateSalary(SalaryDto salaryDto);

    JsonNode createReport(ReportDto reportDto);

    SalaryDto getSalary(Long employeeId);

}
