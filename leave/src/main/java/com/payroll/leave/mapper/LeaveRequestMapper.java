package com.payroll.leave.mapper;

import com.payroll.leave.dto.LeaveDto;
import com.payroll.leave.dto.LeaveRequestDto;
import com.payroll.leave.entity.Leave;
import com.payroll.leave.entity.LeaveRequest;

public class LeaveRequestMapper {

    public static LeaveRequestDto mapToLeaveRequestDto(LeaveRequest leaveRequest, LeaveRequestDto leaveRequestDto) {
        leaveRequestDto.setEmployeeId(leaveRequest.getEmployeeId());
        leaveRequestDto.setStartDate(leaveRequest.getStartDate());
        leaveRequestDto.setEndDate(leaveRequest.getEndDate());
        leaveRequestDto.setManagerId(leaveRequest.getManagerId());
        leaveRequestDto.setRemainingLeaves(leaveRequest.getRemainingLeaves());
        leaveRequestDto.setLwp(leaveRequest.getLwp());
        leaveRequestDto.setStatus(leaveRequest.getStatus());
        leaveRequestDto.setLeaveRequestId(leaveRequest.getLeaveRequestId());
        return leaveRequestDto;
    }

    public static LeaveRequest mapToLeaveRequest(LeaveRequestDto leaveRequestDto, LeaveRequest leaveRequest) {
        leaveRequest.setEmployeeId(leaveRequestDto.getEmployeeId());
        leaveRequest.setStartDate(leaveRequestDto.getStartDate());
        leaveRequest.setEndDate(leaveRequestDto.getEndDate());
        leaveRequest.setManagerId(leaveRequestDto.getManagerId());
//        leaveRequest.setLwp(leaveRequestDto.getLwp());
        return leaveRequest;
    }
}
