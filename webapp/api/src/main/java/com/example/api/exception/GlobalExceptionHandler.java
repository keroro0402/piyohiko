package com.example.api.exception;

import com.example.api.dto.ApiErrorDto;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.List;

/* @RestControllerAdvice：アプリ内の例外をこのクラスで処理する */
@RestControllerAdvice
public class GlobalExceptionHandler {
/* @ExceptionHandler：引数のクラスでエラーが起きたら後述のメソッドで対応する */

    // 400：API のバリデーションに弾かれた時の ExceptionHandler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDto> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request){
        List<String> messageList = e.getBindingResult() // ① 本番バリデーションエラーの全部を横取りで取得
                .getFieldErrors() // ② ①からエラーが起きた項目を取得
                .stream() // ③ ②をエラーファイルを整列させる
                .map(error -> error.getDefaultMessage())  // ④ ③から Form で指定したエラーメッセージを抽出
                .toList(); // ⑤ ④のエラーメッセージから新しいリストを作成する
        ApiErrorDto apiErrorDto = new ApiErrorDto(); // エラー用DTOを実体化して用意する
        apiErrorDto.setErrorCode("VALIDATION_ERROR"); // DTOにErrorCodeをセット
        apiErrorDto.setMessage(messageList); // DTOにエラーメッセージリストをセット
        apiErrorDto.setTimestamp(LocalDateTime.now().toString());
        apiErrorDto.setPath(request.getRequestURI());
        return ResponseEntity.status(400).body(apiErrorDto);
    }

    // 400：API のリクエストで JSON が破損、不正な時の ExceptionHandler
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorDto> handleJsonParseException(HttpMessageNotReadableException e, HttpServletRequest request){
        ApiErrorDto apiErrorDto = new ApiErrorDto();
        apiErrorDto.setErrorCode("INVALID_JSON");
        apiErrorDto.setMessage(List.of("JSONの形式が不正です"));
        apiErrorDto.setTimestamp(LocalDateTime.now().toString());
        apiErrorDto.setPath(request.getRequestURI());
        return ResponseEntity.status(400).body(apiErrorDto);
    }


    // 401：DB未登録データでリクエストした時の ExceptionHandler（使用API例：ログイン("/login")）
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorDto> handleUnauthorizedException(BusinessException e){
        ApiErrorDto apiErrorDto = new ApiErrorDto();
        apiErrorDto.setErrorCode(e.getErrorCode());
        apiErrorDto.setMessage(List.of(e.getMessage()));
        return ResponseEntity.status(401).body(apiErrorDto);
    }

    // 404：存在しないAPIにリクエストした時の ExceptionHandler
    @ExceptionHandler({
            NoResourceFoundException.class, // 最新の404例外
            NoHandlerFoundException.class, // 従来の404例外
            HttpRequestMethodNotSupportedException.class // メソッド不一致例外
    })
    public ResponseEntity<ApiErrorDto> handleNotFoundException(Exception e, HttpServletRequest request){
        ApiErrorDto apiErrorDto = new ApiErrorDto();
        apiErrorDto.setErrorCode("NOT_FOUND");
        apiErrorDto.setMessage(List.of("リクエストされたAPIが存在しません"));
        apiErrorDto.setTimestamp(LocalDateTime.now().toString());
        apiErrorDto.setPath(request.getRequestURI());
        return ResponseEntity.status(404).body(apiErrorDto);
    }


    // 409：DB登録済みの重複するデータでリクエストされた時の ExceptionHandler（使用API例：新規登録("/signup")）
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorDto> handleRegisterException(BusinessException e){
        ApiErrorDto apiErrorDto = new ApiErrorDto();
        apiErrorDto.setErrorCode(e.getErrorCode());
        apiErrorDto.setMessage(List.of(e.getMessage()));
        return ResponseEntity.status(409).body(apiErrorDto);
    }

    /* 500：API 全体で発生した 500（サーバーエラー） のエラーハンドリング用メソッド */
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
