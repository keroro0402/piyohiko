package com.example.api.sandbox.dto;

import lombok.NoArgsConstructor;

/**
 * 状況	newできる？	意味
 * コンストラクタ0個 + NoArgsConstructorあり　→　引数なしでnewができる（Lombokが引数なしのコンストラクタを暗黙に作成する　=　デフォルトコンストラクタの作成はなし）
 */
@NoArgsConstructor
public class NoConstructorYesNoArgs1Dto {
    private NoConstructorYesNoArgs1Status noConstructorYesNoArgs1Status;

    /*セッター*/
    public void setStatus(NoConstructorYesNoArgs1Status noConstructorYesNoArgs1Status){
        this.noConstructorYesNoArgs1Status = noConstructorYesNoArgs1Status;
    }
    /*ゲッター*/
    public NoConstructorYesNoArgs1Status getStatus(){
        return noConstructorYesNoArgs1Status;
    }


}
