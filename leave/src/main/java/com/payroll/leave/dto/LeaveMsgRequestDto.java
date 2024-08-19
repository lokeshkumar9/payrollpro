package com.payroll.leave.dto;

import java.time.LocalDate;

public record LeaveMsgRequestDto(LocalDate startDate, LocalDate endDate) {
}

