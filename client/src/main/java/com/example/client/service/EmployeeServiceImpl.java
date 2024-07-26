package com.example.client.service;

import com.example.client.annotation.FastAsyncTask;
import com.example.client.dao.EmployeeDao;
import com.example.client.dto.AttachPhoneDto;
import com.example.client.dto.EmployeeDto;
import com.example.client.entity.Employee;
import com.example.client.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDao employeeDao;
    private final EmployeeMapper employeeMapper;

    @FastAsyncTask
    @Override
    public CompletableFuture<Long> createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);

        employeeDao.save(employee);

        return CompletableFuture.completedFuture(employee.getId());
    }

    @FastAsyncTask
    @Override
    public CompletableFuture<Boolean> attachPhone(AttachPhoneDto attachPhoneDto) {
        employeeDao.attachPhoneToEmployee(attachPhoneDto.getEmployeeId(), attachPhoneDto.getPhoneId());

        return CompletableFuture.completedFuture(true);
    }
}
