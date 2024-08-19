package com.payroll.leave;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payroll.leave.dto.LeaveDto;
import com.payroll.leave.entity.Leave;
import com.payroll.leave.repository.LeaveRepository;
import com.payroll.leave.repository.LeaveRequestRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class LeaveApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    private Leave leave;

//    @Test
//    @Transactional
//    public void testViewLeavesByEmployeeId() throws Exception {
//
//        Leave leave = new Leave();
//        leave.setEmployeeId(1L);
//        leave.setTotalLeaves(50L);
//        leave.setRemainingLeaves(40L);
//        leave.setLwp(3L);
//        leaveRepository.save(leave);
//
//        mockMvc.perform(get("/leave/viewLeaves" + leave.getEmployeeId())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.totalLeaves").value("50"))
//                .andExpect(jsonPath("$.remainingLeaves").value("40"))
//                .andExpect(jsonPath("$.lwp").value("3"));
//    }

}
