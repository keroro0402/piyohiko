package com.example.api.sandbox.controller;

import com.example.api.sandbox.dto.ProductDto;
import com.example.api.sandbox.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @GetMapping("/product")
    /*戻り値はProductDto*/
    public ProductDto getProduct(
            @RequestParam int id,
            @RequestParam String name
    ){
        /*productServiceが引数を使って、getProductを実行*/
        return productService.getProduct(id, name);
    }
}
