package com.example.api.service;
// 実装クラス ：interface の「約束（メソッド名・戻り値・引数）」を実際にどう動くか具体的に書いたクラスのこと

/*
 * interface を使う（実装した）クラス RegisterServiceImpl を定義
 * implements キーワードで RegisterService インターフェイスに定義されたメソッドを必ず実装することを宣言している
 * 依存先は interface
 * */
public class RegisterServiceImpl implements RegisterService {
    /* @Override
     * interface に定義された register() メソッドを
     * このクラスで具体的に実装（処理内容の決定）する
     * */
    @Override
    public String register() {
        return "トピック登録完了!!!!";
    }
}
