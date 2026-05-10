package com.example.api.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class LoggingAspect {
    // className・methodName用DTOを定義
    @RequiredArgsConstructor
    private static class TargetInfo {
        final String className;
        final String methodName;
    }

    // クラス名・メソッド名をDTOで取得
    private TargetInfo getTargetName(JoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        return new TargetInfo(className, methodName);
    }

    // メソッドが開始した時に実行される後処理ログ
    @Before(value = "execution(* com.example.api.service..*Impl.*(..))")
    @SuppressWarnings("unused") // 使用していないの警告を消す
    public void logBefore(JoinPoint joinPoint){
        TargetInfo info = getTargetName(joinPoint);
        writeLog(info.className, info.methodName, "メソッド開始");
    }

    // メソッドが「正常終了」した時のみ実行される後処理ログ
    @AfterReturning(value = "execution(* com.example.api.service..*Impl.*(..))")
    @SuppressWarnings("unused") // 使用していないの警告を消す
    public void logAfter(JoinPoint joinPoint){
        TargetInfo info = getTargetName(joinPoint);
        writeLog(info.className, info.methodName, "メソッド終了");
    }

    // メソッドが「異常終了」した時のみ実行される後処理ログ
    @AfterThrowing(value = "execution(* com.example.api.service..*Impl.*(..))", throwing = "e")
    @SuppressWarnings("unused") // 使用していないの警告を消す
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e){
        TargetInfo info = getTargetName(joinPoint);
        writeLog(info.className, info.methodName, "【!異常終了!】エラー" + e.getMessage());
    }

    // 共通ログ出力メソッド
    private void writeLog( String className, String methodName, String statusLabel){
        long threadId = Thread.currentThread().getId();
        // 出力時刻文字列取得
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String strNow = LocalDateTime.now().format(formatter);
        String message = String.format(
                "%s [Thread:%d] : クラス：%s, %s ：%s()",
                strNow,
                threadId,
                className,
                statusLabel,
                methodName
        );
        // ログ表示
        System.out.println(message);
    }


}
