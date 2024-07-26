package com.example.client.util;

import com.example.client.dto.reply.ErrorReply;

public interface ErrorReplyStrategy {
    ErrorReply getErrorReply(Exception e);
}
