package com.example.api.exception;

import com.example.api.dto.LoginErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/*@RestControllerAdvice：アプリ内の例外をこのクラスで処理する*/
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*@ExceptionHandler：引数のクラスでエラーが起きたら後述のメソッドで対応する*/
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<LoginErrorDto> handleLoginException(LoginException e){
        LoginErrorDto loginErrorDto = new LoginErrorDto();
        loginErrorDto.setErrorCode(e.getErrorCode());
        loginErrorDto.setMessage(List.of(e.getMessage()));
        return ResponseEntity.status(401).body(loginErrorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<LoginErrorDto> handleValidationException(MethodArgumentNotValidException e){

        List<String> messageList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        LoginErrorDto loginErrorDto = new LoginErrorDto();
        loginErrorDto.setErrorCode("VALIDATION_ERROR");
        loginErrorDto.setMessage(messageList);
        return ResponseEntity.status(400).body(loginErrorDto);
    }
}
