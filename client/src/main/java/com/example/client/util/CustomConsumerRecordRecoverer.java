package com.example.client.util;

import com.example.client.dto.reply.ErrorReply;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConsumerRecordRecoverer;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomConsumerRecordRecoverer implements ConsumerRecordRecoverer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final KafkaTemplate<String, String> template;

    private final Map<String, ErrorReplyStrategy> errorReplyStrategyMap;

    @Override
    @SneakyThrows
    public void accept(ConsumerRecord<?, ?> record, Exception e) {
        log.error("Exception: ", e);

        String topic = new String(record.headers().lastHeader(KafkaHeaders.REPLY_TOPIC).value());

        String key = (String) record.key();

        byte[] correlationId = record.headers().lastHeader(KafkaHeaders.CORRELATION_ID).value();

        String exceptionName = e.getCause().getCause().getClass().getSimpleName();

        ErrorReply errorReply = errorReplyStrategyMap.getOrDefault(exceptionName + "ReplyStrategy", new ExceptionReplyStrategy()).getErrorReply(e);

        String payload = objectMapper.writeValueAsString(errorReply);

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, payload);

        producerRecord.headers().add("REPLY_STATUS", errorReply.getStatus().getBytes());
        producerRecord.headers().add(KafkaHeaders.CORRELATION_ID, correlationId);

        template.send(producerRecord);
    }
}
