package com.payroll.leave.dto;

import com.payroll.leave.constants.Constants;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LeaveRequestDto {

    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long remainingLeaves;
    private Long lwp;
    @Enumerated(EnumType.STRING)
    private Constants.Status status;
    private Long managerId;
    private Long leaveRequestId;

}
