package com.example.client.service;

import com.example.client.annotation.FastAsyncTask;
import com.example.client.dao.PhoneDao;
import com.example.client.dto.PhoneDto;
import com.example.client.entity.Phone;
import com.example.client.mapper.PhoneMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {
    private final PhoneDao phoneDao;
    private final PhoneMapper phoneMapper;

    @FastAsyncTask
    @Override
    public CompletableFuture<Long> createPhone(PhoneDto phoneDto) {
        Phone phone = phoneMapper.toEntity(phoneDto);

        phoneDao.save(phone);

        return CompletableFuture.completedFuture(phone.getId());
    }
}
