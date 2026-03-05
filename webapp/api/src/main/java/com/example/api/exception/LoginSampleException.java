package com.example.api.exception;

/*
* A extends B
* Bの特性（メソッドや値など）をAが引き継ぐ
* */
public class LoginSampleException extends RuntimeException {
    public LoginSampleException(String message) {
        /*
        * super
        * 親クラス(RuntimeException)のコンストラクタを呼ぶ
        * */
        super(message);
    }
}
