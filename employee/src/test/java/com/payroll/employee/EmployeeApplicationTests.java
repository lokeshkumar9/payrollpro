package com.payroll.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payroll.employee.dto.EmployeeDto;
import com.payroll.employee.entity.Employee;
import com.payroll.employee.entity.EmployeeRole;
import com.payroll.employee.enums.Role;
import com.payroll.employee.repository.EmployeeRepository;
import com.payroll.employee.repository.EmployeeRoleRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmployeeRoleRepository employeeRoleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeDto employeeDto;


    @BeforeEach
    public void setUp() {

        employeeRepository.deleteAll();
        employeeRoleRepository.deleteAll();
        employeeDto = new EmployeeDto();
        employeeDto.setFirstName("John");
        employeeDto.setLastName("Doe");
        employeeDto.setDateOfBirth(LocalDate.of(1990, 1, 1));
        employeeDto.setEmail("john.doe@example.com");
        employeeDto.setPhoneNumber("1234567890");
        employeeDto.setJobTitle("Developer");
        employeeDto.setDepartment("IT");
        employeeDto.setManagerId(1L);
        employeeDto.setEmployeeRoles(Arrays.asList(Role.EMPLOYEE, Role.MANAGER));
    }

    @Test
    public void testCreateEmployee() throws Exception {
        mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value("201"))
                .andExpect(jsonPath("$.message").value("Created Successfully"));

        List<Employee> employees = employeeRepository.findAll();
        assertThat(employees).isNotEmpty();

        Employee createdEmployee = employees.get(0);
        assertThat(createdEmployee.getFirstName()).isEqualTo("John");
        assertThat(createdEmployee.getLastName()).isEqualTo("Doe");
        assertThat(createdEmployee.getEmail()).isEqualTo("john.doe@example.com");


        List<Role> roles = employeeRoleRepository.findAllByEmployeeId(createdEmployee.getEmployeeId())
                .orElseThrow()
                .stream()
                .map(EmployeeRole::getRole)
                .toList();
        assertThat(roles).contains(Role.EMPLOYEE, Role.MANAGER);

    }

    @Test
    public void testDeleteEmployee() throws Exception {
        testCreateEmployee();
        Employee createdEmployee = employeeRepository.findAll().get(0);
        Long employeeId = createdEmployee.getEmployeeId();

        mockMvc.perform(delete("/api/delete/{employeeId}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("204"))
                .andExpect(jsonPath("$.message").value("Deleted Successfully"));

        Optional<Employee> deletedEmployee = employeeRepository.findById(employeeId);
        assertFalse(deletedEmployee.isPresent());
    }


    @Test
    @Transactional
    public void testGetEmployeeById() throws Exception {
        testCreateEmployee();
        Employee createdEmployee = employeeRepository.findAll().get(0);


        mockMvc.perform(get("/api/" + createdEmployee.getEmployeeId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        testCreateEmployee();
        Employee createdEmployee = employeeRepository.findAll().get(0);
        Long employeeId = createdEmployee.getEmployeeId();

        employeeDto.setFirstName("Jane");
        employeeDto.setLastName("Smith");

        mockMvc.perform(put("/api/update/{employeeId}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("204"))
                .andExpect(jsonPath("$.message").value("Employee details Updated Successfully"));

        Employee updatedEmployee = employeeRepository.findById(employeeId).orElseThrow();
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Jane");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Smith");
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        testCreateEmployee();

        mockMvc.perform(get("/api/fetch-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"));
    }

    @Test
    @Transactional
    public void testGetEmployeeByManagerId() throws Exception {

        mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value("201"))
                .andExpect(jsonPath("$.message").value("Created Successfully"));

        EmployeeDto secondEmployeeDto = new EmployeeDto();
        secondEmployeeDto.setFirstName("Jane");
        secondEmployeeDto.setLastName("Doe");
        secondEmployeeDto.setDateOfBirth(LocalDate.of(1992, 1, 1));
        secondEmployeeDto.setEmail("jane.doe@example.com");
        secondEmployeeDto.setPhoneNumber("0987654321");
        secondEmployeeDto.setJobTitle("Tester");
        secondEmployeeDto.setDepartment("IT");
        secondEmployeeDto.setManagerId(1L);
        secondEmployeeDto.setEmployeeRoles(Arrays.asList(Role.EMPLOYEE));

        mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(secondEmployeeDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value("201"))
                .andExpect(jsonPath("$.message").value("Created Successfully"));

        mockMvc.perform(get("/api/manager/{managerId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));
    }
}
