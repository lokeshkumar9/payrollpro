package com.payroll.message.function;

import com.payroll.message.dto.LeaveMsgRequestDto;
import com.payroll.message.service.EmailService;
import jakarta.validation.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.time.LocalDate;
import java.util.Properties;
import java.util.function.Function;

@Configuration
public class LeaveFunction {

    private static final Logger logger = LoggerFactory.getLogger(LeaveFunction.class);

    @Autowired
    private EmailService emailService;

    @Bean
    public Function<LeaveMsgRequestDto, LeaveMsgRequestDto> email(){
        return leaveMsgRequestDto -> {
            logger.info("Sending Email with details - {}", leaveMsgRequestDto);

            String subject = "Leave Request Notification";
            String text = String.format("Leave request details: %s", leaveMsgRequestDto.toString());
//            Email email  = leaveMsgRequestDto.getEmail();
            System.out.println(subject);
            emailService.sendEmail("yatishgarg1353@gmail.com", subject, text);

            return leaveMsgRequestDto;
        };
    }

}
