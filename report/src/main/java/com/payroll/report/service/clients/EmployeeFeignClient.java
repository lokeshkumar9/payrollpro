package com.payroll.report.service.clients;

import com.payroll.report.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("EMPLOYEE-MICROSERVICE")
public interface EmployeeFeignClient {

    @GetMapping("/api/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId);

    @GetMapping("/api/fetch-all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeData();
}
