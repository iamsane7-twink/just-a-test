package com.example.client.consumer;

import com.example.client.config.KafkaTopics;
import com.example.client.dto.PhoneDto;
import com.example.client.dto.reply.CreatePhoneReply;
import com.example.client.service.PhoneService;
import com.example.client.util.KafkaReplyMessageBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
@Slf4j
public class PhoneMessageConsumer {
    private final PhoneService phoneService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = KafkaTopics.CREATE_PHONE_TOPIC,
            groupId = "create-phone",
            containerFactory = "concurrentKafkaListenerContainerFactory")
    @SendTo
    public Message<String> createPhone(String data) throws Exception {
        PhoneDto phoneDto = objectMapper.readValue(data, PhoneDto.class);

        CompletableFuture<Long> future = phoneService.createPhone(phoneDto);

        return KafkaReplyMessageBuilder.build(new CreatePhoneReply(future.get()), "201");
    }
}
