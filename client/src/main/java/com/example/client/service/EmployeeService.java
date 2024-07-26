package com.example.client.service;

import com.example.client.dto.AttachPhoneDto;
import com.example.client.dto.EmployeeDto;

import java.util.concurrent.CompletableFuture;

public interface EmployeeService {
    CompletableFuture<Long> createEmployee(EmployeeDto employeeDto);

    CompletableFuture<Boolean> attachPhone(AttachPhoneDto attachPhoneDto);
}
