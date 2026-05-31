package com.example.api.exception;

import com.example.api.dto.ApiErrorDto;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

/*@RestControllerAdvice：アプリ内の例外をこのクラスで処理する*/
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*@ExceptionHandler：引数のクラスでエラーが起きたら後述のメソッドで対応する*/
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ApiErrorDto> handleLoginException(LoginException e){
        ApiErrorDto apiErrorDto = new ApiErrorDto();
        apiErrorDto.setErrorCode(e.getErrorCode());
        apiErrorDto.setMessage(List.of(e.getMessage()));
        return ResponseEntity.status(401).body(apiErrorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDto> handleValidationException(MethodArgumentNotValidException e){

        List<String> messageList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        ApiErrorDto apiErrorDto = new ApiErrorDto();
        apiErrorDto.setErrorCode("VALIDATION_ERROR");
        apiErrorDto.setMessage(messageList);
        return ResponseEntity.status(400).body(apiErrorDto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorDto> handleJsonParseError(HttpMessageNotReadableException e){
        ApiErrorDto dto = new ApiErrorDto();
        dto.setErrorCode("INVALID_JSON");
        dto.setMessage(List.of("JSONの形式が不正です"));
        return ResponseEntity.status(400).body(dto);
    }

    /* Api全体で発生した 500 のエラーハンドリング用メソッド */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDto> handleAllException(Exception e, HttpServletRequest request) {
        ApiErrorDto apiErrorDto = new ApiErrorDto();
        apiErrorDto.setErrorCode("SYSTEM_ERROR");
        apiErrorDto.setMessage(List.of("システムエラーが発生しました。管理者にお問い合わせください。"));
        apiErrorDto.setTimestamp(LocalDateTime.now().toString());
        apiErrorDto.setPath(request.getRequestURI());
        return ResponseEntity.status(500).body(apiErrorDto);
    }


}
