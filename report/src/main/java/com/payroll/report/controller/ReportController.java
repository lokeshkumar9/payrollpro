package com.payroll.report.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.payroll.report.dto.EmployeeDto;
import com.payroll.report.dto.ReportDto;
import com.payroll.report.dto.ResponseDto;
import com.payroll.report.dto.SalaryDto;
import com.payroll.report.service.IReportService;
import com.payroll.report.service.clients.EmployeeFeignClient;
import com.payroll.report.service.clients.LeaveFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
@AllArgsConstructor
public class ReportController {


    private final IReportService reportService;

    @Autowired
    private EmployeeFeignClient employeeFeignClient;

    @Autowired
    private LeaveFeignClient leaveFeignClient;

    @PostMapping("/create")
    public ResponseEntity<JsonNode> createReport(@RequestBody ReportDto reportDto)
    {
        JsonNode response = reportService.createReport(reportDto);
        if (response != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateSalaryDetails(@RequestBody SalaryDto salaryDto)
    {
        boolean isUpdated = reportService.updateSalary(salaryDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("204", "Updated successfully"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto("500", "Internal Server Error"));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam Long employeeId)
    {
        boolean isDeleted = reportService.deleteAccount(employeeId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("204", "Deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("500", "Internal Server Error"));
        }
    }

    @GetMapping("/getEmployeeDetails/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeDetails(@PathVariable Long employeeId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeFeignClient.getEmployeeById(employeeId).getBody());
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeFeignClient.getAllEmployeeData().getBody());
    }

    @GetMapping("/fetchlwp")
    public ResponseEntity<Long> fetchLwp(@RequestParam Long employeeId , @RequestParam String startDate , @RequestParam String endDate){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(leaveFeignClient.fetchLwp(employeeId, startDate, endDate).getBody());
    }
    @GetMapping("/getsalary")
    public ResponseEntity<SalaryDto>getSalary(@RequestParam Long employeeId)
    {
        SalaryDto salaryDto= reportService.getSalary(employeeId);
        return   ResponseEntity.status(HttpStatus.OK).body(salaryDto);
    }
}
