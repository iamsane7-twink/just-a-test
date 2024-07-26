package com.example.server.controller;

import com.example.server.dto.AttachPhoneDto;
import com.example.server.dto.EmployeeDto;
import com.example.server.dto.PhoneDto;
import com.example.server.service.KafkaMessageProducerImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.server.config.KafkaTopics.*;

@RestController
@AllArgsConstructor
public class Controller {
    private final KafkaMessageProducerImpl kafkaMessageProducer;

    @PostMapping(value = "api/v1/employees",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {

        ConsumerRecord<String, String> consumerRecord = kafkaMessageProducer.sendAndReceive(CREATE_EMPLOYEE_TOPIC, CREATE_EMPLOYEE_REPLY, employeeDto);

        return convertToResponse(consumerRecord);
    }

    @PutMapping(value = "api/v1/employees/attach-phone",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> attachPhone(@RequestBody @Valid AttachPhoneDto attachPhoneDto) {

        ConsumerRecord<String, String> consumerRecord = kafkaMessageProducer.sendAndReceive(EMPLOYEE_ATTACH_PHONE_TOPIC, EMPLOYEE_ATTACH_PHONE_REPLY, attachPhoneDto);

        return convertToResponse(consumerRecord);
    }

    @PostMapping(value = "api/v1/phones",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPhone(@RequestBody @Valid PhoneDto phoneDto) {

        ConsumerRecord<String, String> consumerRecord = kafkaMessageProducer.sendAndReceive(CREATE_PHONE_TOPIC, CREATE_PHONE_REPLY, phoneDto);

        return convertToResponse(consumerRecord);
    }

    private ResponseEntity<String> convertToResponse(ConsumerRecord<String, String> consumerRecord) {
        int status = Integer.parseInt(new String(consumerRecord.headers().lastHeader("REPLY_STATUS").value()));

        String payload = consumerRecord.value();

        return ResponseEntity.status(status).body(payload);
    }
}
