package com.example.api.sandbox.controller;

import com.example.api.sandbox.dto.Product2Dto;
import com.example.api.sandbox.service.Product2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Product2Controller {

    private final Product2Service product2Service;

    @GetMapping("/product2")
    /*戻り値はProduct2Dto*/
    public Product2Dto getProduct(
            @RequestParam int id,
            @RequestParam String name
    ){
        /*product2Serviceが引数を使って、getProduct2を実行*/
        return product2Service.getProduct2(id, name);
    }
}



