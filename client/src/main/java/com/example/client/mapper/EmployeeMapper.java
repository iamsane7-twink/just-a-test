package com.example.client.mapper;

import com.example.client.dto.EmployeeDto;
import com.example.client.entity.Employee;
import org.mapstruct.Mapper;

@Mapper
public interface EmployeeMapper {
    Employee toEntity(EmployeeDto dto);
}
