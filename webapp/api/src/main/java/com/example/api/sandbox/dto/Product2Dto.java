package com.example.api.sandbox.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
/*@NoArgsConstructor：デフォルトコンストラクタ。JSONデシリアライズ用。
* インスタンス化時には引数は入れてはいけない。
* 値は setter で自動で入れられる（インスタンス作成時）
* POSTメソッドでリクエストボディをJSONで返す時に使う。返却するJSONに追加のフィールドがある場合はセッターで追加する
* */
//@NoArgsConstructor
public class Product2Dto {
    private int id;
    private String name;
    private int price;
/*@NoArgsConstructorなしだと以下コンストラクタの記述がいる*/
    // public Product2Dto(){}
}
