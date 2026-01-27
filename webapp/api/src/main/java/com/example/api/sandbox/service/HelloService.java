package com.example.api.sandbox.service;

import com.example.api.sandbox.dto.HelloDto;
import org.springframework.stereotype.Service;

/*@Service：ステレオタイプアノテーションの一種。
* SpringコンテナにこのクラスをBeanとして登録するために必須。
* */
//@Service
public class HelloService {
    public HelloDto getHello(String messageHello){
        /*newしてHelloDtoコンストラクタを実体化・初期化する*/
        return new HelloDto(messageHello);
    }
}
