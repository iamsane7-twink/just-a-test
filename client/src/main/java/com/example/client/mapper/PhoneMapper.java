package com.example.client.mapper;

import com.example.client.dto.PhoneDto;
import com.example.client.entity.Phone;
import org.mapstruct.Mapper;

@Mapper
public interface PhoneMapper {
    Phone toEntity(PhoneDto dto);
}
