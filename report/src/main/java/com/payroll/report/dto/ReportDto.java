package com.payroll.report.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportDto {
    private String employeeName;
    private Long employeeId;
    private String startDate;
    private String endDate;
}
