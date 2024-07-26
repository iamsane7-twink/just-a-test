package com.example.server.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.springframework.kafka.support.KafkaHeaders.*;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaMessageProducerImpl implements KafkaMessageProducer {
    private final ReplyingKafkaTemplate<String, Object, String> replyingKafkaTemplate;

    @Override
    public ConsumerRecord<String, String> sendAndReceive(String topic, String replyTopic, Object payload) {
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic, UUID.randomUUID().toString(), payload);

        producerRecord.headers().add(new RecordHeader(REPLY_TOPIC, replyTopic.getBytes()));

        RequestReplyFuture<String,Object,String> requestReplyFuture = replyingKafkaTemplate.sendAndReceive(producerRecord);

        try {
            return requestReplyFuture.get();
        } catch (InterruptedException e) {
            log.error("Thread interrupted", e);

            Thread.currentThread().interrupt();

            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("Execution exception", e);

            throw new RuntimeException(e);
        }
    }
}
