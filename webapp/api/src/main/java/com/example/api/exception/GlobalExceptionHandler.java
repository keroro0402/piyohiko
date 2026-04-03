package com.example.api.exception;

import com.example.api.dto.LoginErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*@RestControllerAdvice：アプリ内の例外をこのクラスで処理する*/
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*@ExceptionHandler：引数のクラスでエラーが起きたら後述のメソッドで対応する*/
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<LoginErrorDto> handleLoginException(LoginException e){
        LoginErrorDto loginErrorDto = new LoginErrorDto();
        loginErrorDto.setErrorCode(e.getErrorCode());
        loginErrorDto.setMessage(e.getMessage());
        return ResponseEntity.status(401).body(loginErrorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<LoginErrorDto> handleValidationException(MethodArgumentNotValidException e){
        LoginErrorDto loginErrorDto = new LoginErrorDto();
        loginErrorDto.setErrorCode("VALIDATION_ERROR");
        loginErrorDto.setMessage(e.getBindingResult().getFieldError().getDefaultMessage());
        return ResponseEntity.status(400).body(loginErrorDto);
    }
}
