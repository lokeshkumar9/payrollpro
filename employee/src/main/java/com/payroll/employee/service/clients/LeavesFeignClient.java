package com.payroll.employee.service.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("leaves")
public interface LeavesFeignClient {
}
