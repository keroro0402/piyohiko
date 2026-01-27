package com.example.api.sandbox.service;

import com.example.api.sandbox.dto.ProductDto;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public ProductDto getProduct(int id, String name){
        int price = 100;
        /*ここで@AllArgsConstructorのコンストラクタが呼ばれる
        * AllArgsConstructorは引数を勝手にフィールドにセットするので、セッターで設定しない
        * */
        return new ProductDto(id, name, price);
    }
}
