package com.example.api.sandbox.dto;

import lombok.NoArgsConstructor;

/**
 * 状況	newできる？	意味
 * コンストラクタ1個以上 + NoArgsConstructorあり　→　引数ありなしの両方でnewができる（コンストラクタを作成/Lambok
 * がコンストラクタを作成する　＝　デフォルトコンストラクタ作成されない）
 */
@NoArgsConstructor
public class YesConstructorYesNoArgs1Dto {
    private YesConstructorYesNoArgs1Status yesConstructorYesNoArgs1Status;

    /* コンストラクタ*/
    public YesConstructorYesNoArgs1Dto(YesConstructorYesNoArgs1Status yesConstructorYesNoArgs1Status){
        this.yesConstructorYesNoArgs1Status = yesConstructorYesNoArgs1Status;
    }

    /*セッター*/
    public void setStatus(YesConstructorYesNoArgs1Status yesConstructorYesNoArgs1Status){
        this.yesConstructorYesNoArgs1Status = yesConstructorYesNoArgs1Status;
    }

    /*ゲッター*/
    public YesConstructorYesNoArgs1Status getYStatus(){
        return this.yesConstructorYesNoArgs1Status;
    }
}

