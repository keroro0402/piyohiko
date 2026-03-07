package com.example.api.exception;

import com.example.api.dto.LoginErrorDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*@RestControllerAdvice：アプリ内の例外をこのクラスで処理する*/
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*@ExceptionHandler：引数のクラスでエラーが起きたら後述のメソッドで対応する*/
    @ExceptionHandler(LoginException.class)
    public LoginErrorDto handleLoginException(LoginException e){
        LoginErrorDto loginErrorDto = new LoginErrorDto();
        loginErrorDto.setErrorCode("LOGIN_FAILED");
        loginErrorDto.setMessage("ログイン失敗");
        return loginErrorDto;
    }
}
