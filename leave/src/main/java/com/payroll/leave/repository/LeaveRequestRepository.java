package com.payroll.leave.repository;

import com.payroll.leave.entity.Leave;
import com.payroll.leave.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeIdAndStartDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate);
    Optional<LeaveRequest> findByLeaveRequestId(Long leaveRequestId);
    List<LeaveRequest> findByManagerId(Long managerId);

}
