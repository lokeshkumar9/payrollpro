package com.payroll.leave.dto;

import com.payroll.leave.constants.Constants;
import com.payroll.leave.entity.LeaveRequest;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class NotificationResponseDto {
    private Long employeeId;
    private Long leaveRequestId;
    @Enumerated(EnumType.STRING)
    private Constants.Status status;
}
