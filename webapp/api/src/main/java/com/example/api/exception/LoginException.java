package com.example.api.exception;

import lombok.Getter;

@Getter
public class LoginException extends RuntimeException{

    private final String errorCode;

    public LoginException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

}
