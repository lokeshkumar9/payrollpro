package com.payroll.report.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiKeyConfig {

    @Value("${api.key}")
    private String apiKey;

    @Bean
    public String apiKey() {
        return apiKey;
    }
}
