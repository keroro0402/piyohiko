package com.example.api.sandbox.controller;

import com.example.api.sandbox.dto.HelloDto;
import com.example.api.sandbox.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*@RestController：HTTPリクエストを受け取り、レスポンスをJSONで返す*/
//@RestController
public class HelloController {

    /*HelloService型のhelloServiceフィールド確保*/
    private final HelloService helloService;

    /*コンストラクタインジェクション：SpringがHelloServiceのBeanを自動的に渡してくれる
    *DIするとも言う
    * */
    public HelloController(HelloService helloService){
        this.helloService = helloService;
    }
    /*@GetMapping:HTTP GETでアクセスする場合に使用。*/
//    @GetMapping("/hello")
    public HelloDto getHello(){
        /*helloServiceがgetHelloメソッドを実行してHelloDtoのインスタンスを生成して返す*/
        return helloService.getHello("hello");
    }
}
