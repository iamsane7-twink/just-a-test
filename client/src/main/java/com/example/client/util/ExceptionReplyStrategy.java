package com.example.client.util;

import com.example.client.dto.reply.ErrorReply;
import org.springframework.stereotype.Component;

@Component(value = "ExceptionReplyStrategy")
public class ExceptionReplyStrategy implements ErrorReplyStrategy {
    @Override
    public ErrorReply getErrorReply(Exception e) {
        return ErrorReply.builder()
                .status("500")
                .message("Server error")
                .build();
    }
}
