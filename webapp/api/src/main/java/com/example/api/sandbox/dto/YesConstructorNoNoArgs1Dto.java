package com.example.api.sandbox.dto;

/**
 * 状況	newできる？	意味
 * 引数ありコンストラクタ1個以上 + NoArgsConstructorなし　→　引数ありでのみnewができる（デフォルトコンストラクタは作成されない）
 */
public class YesConstructorNoNoArgs1Dto {
    private YesConstructorNoNoArgs1Status yesConstructorNoNoArgs1Status;

    /* コンストラクタ*/
    public YesConstructorNoNoArgs1Dto(YesConstructorNoNoArgs1Status yesConstructorNoNoArgs1Status){
        this.yesConstructorNoNoArgs1Status = yesConstructorNoNoArgs1Status;
    }

    /*セッター*/
    public void setStatus(YesConstructorNoNoArgs1Status yesConstructorNoNoArgs1Status){
        this.yesConstructorNoNoArgs1Status = yesConstructorNoNoArgs1Status;
    }

    /*ゲッター*/
    public YesConstructorNoNoArgs1Status getYStatus(){
        return this.yesConstructorNoNoArgs1Status;
    }
}
