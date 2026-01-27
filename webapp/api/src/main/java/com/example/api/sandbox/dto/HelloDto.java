package com.example.api.sandbox.dto;

import lombok.Data;

//@Data
public class HelloDto {
    /*フィールド確保：このDTOが保持するmessageフィールドを確保する*/
    private String message;
    /*コンストラクタ作成
    * コンストラクタ = 初期設定（実態はメソッド）
    * this = このクラスのインスタンス自身
    * this.message = messageHelloはmessageフィールドに引数messsageHelloをセットするを意味する
    * 期待値：{message : messageHello}
    * */
    public HelloDto(String messageHello){
        this.message = messageHello;
    }
}
