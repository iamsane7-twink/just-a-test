package com.example.server.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopics {
    public static final String CREATE_EMPLOYEE_TOPIC = "employee.create-employee";
    public static final String CREATE_PHONE_TOPIC = "phone.create-phone";
    public static final String EMPLOYEE_ATTACH_PHONE_TOPIC = "employee.attach-phone";
    public static final String CREATE_EMPLOYEE_REPLY = "employee.create-employee-reply";
    public static final String CREATE_PHONE_REPLY = "phone.create-phone-reply";
    public static final String EMPLOYEE_ATTACH_PHONE_REPLY = "employee.attach-phone-reply";

    @Value("${kafkaTopicPartitions}")
    private int numPartitions;
    @Value("${kafkaTopicReplication}")
    private short replication;


    @Bean
    public NewTopic employeeCreate() {
        return new NewTopic(CREATE_EMPLOYEE_TOPIC, numPartitions, replication);
    }

    @Bean
    public NewTopic phoneCreate() {
        return new NewTopic(CREATE_PHONE_TOPIC, numPartitions, replication);
    }

    @Bean
    public NewTopic employeeAttachPhone() {
        return new NewTopic(EMPLOYEE_ATTACH_PHONE_TOPIC, numPartitions, replication);
    }

    @Bean
    public NewTopic createEmployeeReply() {
        return new NewTopic(CREATE_EMPLOYEE_REPLY, numPartitions, replication);
    }

    @Bean
    public NewTopic createPhoneReply() {
        return new NewTopic(CREATE_PHONE_REPLY, numPartitions, replication);
    }

    @Bean
    public NewTopic employeeAttachPhoneReply() {
        return new NewTopic(EMPLOYEE_ATTACH_PHONE_REPLY, numPartitions, replication);
    }
}
