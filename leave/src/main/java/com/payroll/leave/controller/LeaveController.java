package com.payroll.leave.controller;

import com.payroll.leave.dto.*;
import com.payroll.leave.service.ILeaveService;
import com.payroll.leave.service.clients.EmployeeFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/leave")
@AllArgsConstructor
public class LeaveController {

    private final ILeaveService iLeaveService;


    @GetMapping("/s")
    public String a()
    {
        return "S";
    }

    @Autowired
    private EmployeeFeignClient employeeFeignClient;

    @GetMapping("/viewLeaves")
    public ResponseEntity<LeaveDto> viewLeaves(@RequestParam Long employeeId){
        LeaveDto leaveDto = iLeaveService.viewLeaves(employeeId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(leaveDto);
    }
    @PostMapping("/leaveRequest")
    public ResponseEntity<ResponseDto> leaveRequest(@RequestBody LeaveRequestDto leaveRequestDto){
        System.out.println(leaveRequestDto.getEmployeeId());
        boolean isCreated = iLeaveService.generateLeaveRequest(leaveRequestDto);
        if(isCreated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("204" , "Leave Request Created Successfully"));
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto("500", "Failed to create leave request"));
    }

    @GetMapping("/fetchlwp")
    public ResponseEntity<Long> fetchLwp(@RequestParam Long employeeId , @RequestParam String startDate , @RequestParam String endDate){
        System.out.println("Link active");
        System.out.println(startDate);
        System.out.println(endDate);

        // Convert the startDate and endDate strings to LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Trim any leading or trailing whitespace from the date strings
        startDate = startDate.trim();
        endDate = endDate.trim();

        // Convert the startDate and endDate strings to LocalDate
        LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, formatter);
        System.out.println(startLocalDate);
        System.out.println(endLocalDate);


        Long lwpCount = iLeaveService.fetchLwp(employeeId , startLocalDate , endLocalDate);
        return ResponseEntity.status(HttpStatus.OK).body(lwpCount);
    }

    @PostMapping("/approveleave")
    public ResponseEntity<ResponseDto> approveLeave(@RequestBody NotificationResponseDto notificationResponseDto){
        System.out.println("Entered");
        boolean isApproved = false;
        isApproved = iLeaveService.approveLeave(notificationResponseDto);

        if(isApproved){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("200" , "Leave Request Approved"));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Leave Request Declined"));
    }

    @GetMapping("/fetchleaverequest")
    public  ResponseEntity<List<LeaveRequestDto>> fetchLeaveRequest(@RequestParam Long managerId){

        List<LeaveRequestDto> leaveRequestDtos = new ArrayList<>();
        leaveRequestDtos = iLeaveService.fetchLeaveRequest(managerId);
        return  ResponseEntity.status(HttpStatus.OK).body(leaveRequestDtos);
    }

    @GetMapping("/getEmployeeDetails/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeDetails(@PathVariable Long employeeId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeFeignClient.getEmployeeById(employeeId).getBody());
    }

}
