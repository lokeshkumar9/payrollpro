package com.payroll.leave.mapper;
import com.payroll.leave.dto.LeaveDto;
import com.payroll.leave.entity.Leave;

public class LeaveMapper {
    public static LeaveDto mapToLeaveDto(Leave leave , LeaveDto leaveDto){
        leaveDto.setEmployeeId(leave.getEmployeeId());
        leaveDto.setTotalLeaves(leave.getTotalLeaves());
        leaveDto.setRemainingLeaves(leave.getRemainingLeaves());
        leaveDto.setLwp(leave.getLwp());
        return leaveDto;
    }

    public static Leave mapToLeave(LeaveDto leaveDto , Leave leave){
        leave.setEmployeeId(leaveDto.getEmployeeId());
        leave.setTotalLeaves(leaveDto.getTotalLeaves());
        leave.setRemainingLeaves(leaveDto.getRemainingLeaves());
        leave.setLwp(leaveDto.getLwp());
        return  leave;
    }


}
