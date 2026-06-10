package com.example.api.exception;

import lombok.Getter;

@Getter
public class DuplicateUserException extends RuntimeException {

    private final String errorCode;

    public DuplicateUserException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
