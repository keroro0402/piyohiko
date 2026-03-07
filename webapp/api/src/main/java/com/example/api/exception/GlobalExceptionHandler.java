package com.example.api.exception;

import com.example.api.dto.LoginErrorDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/*@RestControllerAdvice：アプリ内の例外をこのクラスで処理する*/
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*@ExceptionHandler：引数のクラスでエラーが起きたら後述のメソッドで対応する*/
    @ExceptionHandler(LoginException.class)
    public Map<String, String> handleLoginException(LoginException e){
        return Map.of(
                "message", e.getMessage()
        );
    }
}
