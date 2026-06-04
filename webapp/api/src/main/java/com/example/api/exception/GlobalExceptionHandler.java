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

/* @RestControllerAdvice：アプリ内の例外をこのクラスで処理する */
@RestControllerAdvice
public class GlobalExceptionHandler {
/* @ExceptionHandler：引数のクラスでエラーが起きたら後述のメソッドで対応する */

    // DB未登録データでリクエストした時の ExceptionHandler（使用API例：ログイン("/login")）
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ApiErrorDto> handleLoginException(LoginException e){
        ApiErrorDto apiErrorDto = new ApiErrorDto();
        apiErrorDto.setErrorCode(e.getErrorCode());
        apiErrorDto.setMessage(List.of(e.getMessage()));
        return ResponseEntity.status(401).body(apiErrorDto);
    }

    // DB登録済みの重複するデータでリクエストされた時の ExceptionHandler（使用API例：新規登録("/signup")）
    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<ApiErrorDto> handleRegisterException(DuplicateUserException e){
        ApiErrorDto apiErrorDto = new ApiErrorDto();
        apiErrorDto.setErrorCode(e.getErrorCode());
        apiErrorDto.setMessage(List.of(e.getMessage()));
        return ResponseEntity.status(409).body(apiErrorDto);
    }

    // API のバリデーションに弾かれた時の ExceptionHandler
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

    // API のリクエストで JSON が破損、不正な時の ExceptionHandler
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorDto> handleJsonParseError(HttpMessageNotReadableException e){
        ApiErrorDto dto = new ApiErrorDto();
        dto.setErrorCode("INVALID_JSON");
        dto.setMessage(List.of("JSONの形式が不正です"));
        return ResponseEntity.status(400).body(dto);
    }

    /* API 全体で発生した 500（サーバーエラー） のエラーハンドリング用メソッド */
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
