package com.payroll.employee.controller;

import com.payroll.employee.dto.*;
import com.payroll.employee.service.IEmployeeService;
import com.payroll.employee.service.impl.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for Employee Payroll",
        description = "CRUD REST APIs in Employee to CREATE, UPDATE, FETCH AND DELETE Employee details"
)

@Validated
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;
    private  final IEmployeeService iEmployeeService;

    @GetMapping("/ping")
    public ResponseEntity<String> Ping() {
        return ResponseEntity.status(HttpStatus.OK).body("Server is Up!");
    }


    @Operation(
            summary = "Create Employee REST API",
            description = "REST API to create new Employee in Payroll"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        employeeService.createEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201", "Created Successfully"));
    }

    @Operation(
            summary = "Delete Employee Details REST API",
            description = "REST API to delete Employee details based on a Employee Id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete/{employeeId}")
    public  ResponseEntity<ResponseDto> deleteEmployee(@PathVariable @Positive Long employeeId) {
        boolean isDeleted = iEmployeeService.deleteEmployee(employeeId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("204", "Deleted Successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("500", "Internal Server Error"));
        }

    }

    @Operation(
            summary = "Fetch Employee Details REST API",
            description = "REST API to fetch Employee details based on a Employee ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId){ // Not sure if Long will work or have to use String
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
    }

    @Operation(
            summary = "Update Employee Details REST API",
            description = "REST API to update Employee details based on Employee Id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch-all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeData(){
        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployeeData();
        return ResponseEntity.status(HttpStatus.OK).body(employeeDtoList);
    }

    @Modifying
    @Transactional
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<ResponseDto> updateEmployee(@PathVariable Long employeeId,@Valid @RequestBody EmployeeDto employeeDto){
        boolean isUpdated= employeeService.updateEmployee(employeeId, employeeDto);
        if(isUpdated)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("204", "Employee details Updated Successfully"));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("500", "Failure to update Employee"));
    }

    @Operation(
            summary = "Fetch All Employee Details Under A Manager REST API",
            description = "REST API to fetch all Employees details based on a Manager ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("manager/{managerId}")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByManagerId(@PathVariable Long managerId){ // Not sure if Long will work or have to use String
        List<EmployeeDto> employeeDtoList = employeeService.getEmployeesByManagerId(managerId);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDtoList);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(@RequestBody LoginRequestDto loginRequestDto){
        LoginDto loginDto= employeeService.login(loginRequestDto);
        if(loginDto != null)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(loginDto);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(null);
    }
}