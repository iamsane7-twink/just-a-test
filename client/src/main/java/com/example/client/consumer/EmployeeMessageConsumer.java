package com.example.client.consumer;

import com.example.client.config.KafkaTopics;
import com.example.client.dto.AttachPhoneDto;
import com.example.client.dto.EmployeeDto;
import com.example.client.dto.reply.CreateEmployeeReply;
import com.example.client.service.EmployeeService;
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
public class EmployeeMessageConsumer {
    private final EmployeeService employeeService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = KafkaTopics.CREATE_EMPLOYEE_TOPIC,
            groupId = "create-employee",
            containerFactory = "concurrentKafkaListenerContainerFactory")
    @SendTo
    public Message<String> createEmployee(String data) throws Exception {
        EmployeeDto employeeDto = objectMapper.readValue(data, EmployeeDto.class);

        CompletableFuture<Long> future = employeeService.createEmployee(employeeDto);

        return KafkaReplyMessageBuilder.build(new CreateEmployeeReply(future.get()), "201");
    }

    @KafkaListener(topics = KafkaTopics.EMPLOYEE_ATTACH_PHONE_TOPIC,
            groupId = "employee-attach-phone",
            containerFactory = "concurrentKafkaListenerContainerFactory")
    @SendTo
    public Message<String> attachPhoneToEmployee(String data) throws Exception {
        AttachPhoneDto dto = objectMapper.readValue(data, AttachPhoneDto.class);

        CompletableFuture<Boolean> future = employeeService.attachPhone(dto);

        future.get();

        return KafkaReplyMessageBuilder.build(data, "200");
    }
}
