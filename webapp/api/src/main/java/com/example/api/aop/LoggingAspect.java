package com.example.api.aop;

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
    @Before("execution(* com.example.api.service..*Impl.*(..))")
    public void logBefore(JoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        outputLog("実行開始メソッド", className, methodName);
    }

    // メソッドが「正常終了」した時のみ実行される後処理ログ
    @AfterReturning("execution(* com.example.api.service..*Impl.*(..))")
    public void logAfter(JoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        outputLog("実行済みメソッド", className, methodName);
    }

    // メソッドが「異常終了」した時のみ実行される後処理ログ
    @AfterThrowing(value = "execution(* com.example.api.service..*Impl.*(..))", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e){
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        outputErrorLog( "エラー：" + e.getMessage(), className, methodName);
    }

    // 正常終了ログ出力メソッド
    private void outputLog( String str, String className, String methodName){
        long threadId = Thread.currentThread().getId();
        // 出力時刻文字列取得
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String strNow = LocalDateTime.now().format(formatter);
        // ログ表示
        System.out.println(
                strNow + " [Thread:" + threadId + "] : " + "クラス" + " : " + className + " , " + str + " : " + methodName + "()"
        );
    }

    // 異常終了ログ出力メソッド
    private void outputErrorLog( String className, String methodName, String error){
        long threadId = Thread.currentThread().getId();
        // 出力時刻文字列取得
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String strNow = LocalDateTime.now().format(formatter);
        // エラーログ表示
        System.out.println(
                strNow + " [Thread:" + threadId + "] : " + "クラス" + " : " + className + " , " + "【エラー発生】メソッド" + " : " + methodName + "()"
        );
    }


}
