package com.payroll.leave.entity;

import com.payroll.leave.constants.Constants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave_request")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {

//    public enum Status {
//        APPROVED, PENDING , DECLINED;
//    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveRequestId;
//    @NotNull
    private Long employeeId;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    private Long remainingLeaves;
    private Long lwp;
    @Enumerated(EnumType.STRING)
    private Constants.Status status;
    private Long managerId;
}
