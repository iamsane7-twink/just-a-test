package com.example.client.service;

import com.example.client.dto.PhoneDto;

import java.util.concurrent.CompletableFuture;

public interface PhoneService {
    CompletableFuture<Long> createPhone(PhoneDto phoneDto);
}
