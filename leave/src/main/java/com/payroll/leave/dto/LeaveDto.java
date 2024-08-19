package com.payroll.leave.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveDto {

    private Long employeeId;

    private Long totalLeaves;

    private Long remainingLeaves;

    private Long lwp;
}
