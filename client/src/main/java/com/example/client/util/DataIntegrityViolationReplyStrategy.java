package com.example.client.util;

import com.example.client.dto.reply.ErrorReply;
import org.springframework.stereotype.Component;

@Component(value = "DataIntegrityViolationExceptionReplyStrategy")
public class DataIntegrityViolationReplyStrategy implements ErrorReplyStrategy {
    @Override
    public ErrorReply getErrorReply(Exception e) {
        String message = e.getCause().getCause().getMessage();

        int index = message.indexOf("Detail:") + 8;
        int lastIndex = message.indexOf("]", index);

        message = message.substring(index, lastIndex);

        return ErrorReply.builder()
                .status("400")
                .message(message)
                .build();
    }
}
