package com.example.server.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaMessageProducer {
    ConsumerRecord<String, String> sendAndReceive(String topic, String replyTopic, Object payload);
}
