package com.payroll.message.dto;

import java.time.LocalDate;

public record LeaveMsgRequestDto(LocalDate startDate, LocalDate endDate) {
}

