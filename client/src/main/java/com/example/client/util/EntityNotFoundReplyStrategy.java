package com.example.client.util;

import com.example.client.dto.reply.ErrorReply;
import org.springframework.stereotype.Component;

@Component(value = "EntityNotFoundExceptionReplyStrategy")
public class EntityNotFoundReplyStrategy implements ErrorReplyStrategy {
    @Override
    public ErrorReply getErrorReply(Exception e) {
        String message = e.getCause().getMessage();

        int last = message.lastIndexOf('.');
        message = message.substring(last + 1);

        message = "Unable to find " + message;

        return ErrorReply.builder()
                .status("404")
                .message(message)
                .build();
    }
}
