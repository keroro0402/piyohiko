package com.example.api.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Data
/*@AllArgsConstructor:全フィールドを初期化するコンストラクタを作成してくれる。引数は必須。
*インスタンス化する時にsetterで値をコンストラクタに入れなくて良い
* JSON デシリアライズ時には使われない
* */
//@AllArgsConstructor
public class ProductDto {
    private int id;
    private String name;
    private int price;

    /*@AllArgsConstructorなしだと以下コンストラクタの記述がいる*/
//    public ProductDto(int id, String name, int price){
//        this.id =id;
//        this.name = name;
//        this.price = price;
//    }
}
