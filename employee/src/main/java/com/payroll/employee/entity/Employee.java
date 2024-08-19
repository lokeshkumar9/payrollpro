package com.payroll.employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class    Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotNull(message = "First name is required")
    private String firstName;

    @NotNull(message = "Last name is required")
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Email can not be empty")
    @Email(message = "Email should be in proper format")
    private String email;

    @NotNull(message = "Mobile number can not be empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number should have ten digits")
    private String phoneNumber;

    @NotNull(message = "Job title can not empty")
    private String jobTitle;

    @NotNull(message = "Department can not be empty")
    private String department;

    @NotNull(message = "Manager Id can not be empty")
    private Long managerId;
}
