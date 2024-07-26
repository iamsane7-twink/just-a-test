package com.example.client.dto.reply;

public enum ErrorReplies {
    DATAINTEGRITYVIOLATIONEXCEPTION("400", "Constraint violation"),
    ENTITYNOTFOUNDEXCEPTION("404", "Entity not found"),
    EXCEPTION("500", "Server error");

    public final String status;

    public final String message;

    ErrorReplies(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorReplies getOrDefault(String name) {
        try {
            return ErrorReplies.valueOf(name.toUpperCase());
        } catch(IllegalArgumentException e) {
            return ErrorReplies.EXCEPTION;
        }
    }
}
