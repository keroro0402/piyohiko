package com.example.api.sandbox.service;

import com.example.api.sandbox.dto.Product2Dto;
import org.springframework.stereotype.Service;

@Service
public class Product2Service {
    public Product2Dto getProduct2(int id, String name){
        Product2Dto dto = new Product2Dto(); //@NoArgsConstructorの時は引数は入れない
        /*成果物のフォールドに値が必要なら、手動でセッターを使って設定する*/
        dto.setId(1);
        dto.setName("Apple");
        dto.setPrice(100);

        return dto;
    }
}
