package com.example.api.sandbox.dto;

import lombok.NoArgsConstructor;

/**
 * 状況	newできる？	意味
 * コンストラクタ0個 + NoArgsConstructorなし　→　引数なしでnewができる(Javaがデフォルトコンストラクタを作成する)
 */

public class NoConstructorNoNoArgs1Dto {
    private NoConstructorNoNoArgs1Status noConstructorNoNoArgs1Status;

    /*セッター*/
    public void setStatus(NoConstructorNoNoArgs1Status noConstructorNoNoArgs1Status){
        this.noConstructorNoNoArgs1Status = noConstructorNoNoArgs1Status;
    }

    /*ゲッター*/
    public NoConstructorNoNoArgs1Status getStatus(){
        return this.noConstructorNoNoArgs1Status;
    }

    /*これを追加してもエラーになる*/
//    public NoConstructorNoNoArgs1Dto(NoConstructorNoNoArgs1Status noConstructorNoNoArgs1Status) {
//        this.noConstructorNoNoArgs1Status = noConstructorNoNoArgs1Status;
//    }

}
