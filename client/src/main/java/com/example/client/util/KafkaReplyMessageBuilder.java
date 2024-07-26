package com.example.client.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;

import static com.example.client.dto.reply.ReplyHeaders.*;

@UtilityClass
public class KafkaReplyMessageBuilder {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Message<String> build(Object payload, String replyStatus) throws JsonProcessingException {
        String body = payload instanceof String ? (String) payload : mapper.writeValueAsString(payload);

        return MessageBuilder.createMessage(body, new MessageHeaders(Map.of(REPLY_STATUS, replyStatus.getBytes())));
    }
}
