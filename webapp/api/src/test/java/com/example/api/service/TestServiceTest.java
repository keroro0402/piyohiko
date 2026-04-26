package com.example.api.service; // テスト対象と同じパッケージ名

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// classes = にはテスト対象クラスそのものを指定（Security等の回避モード）
@SpringBootTest(classes = TestService.class)
public class TestServiceTest { // クラス名は「テスト対象クラス名 + Test」

    // テスト対象
    @Autowired
    private TestService testService;

    @Test
     public void testGetHello(){ // テストしたいメソッド名
        String result = testService.getHello();

        System.out.println("----------------------------------------");
        System.out.println("テスト結果：");
        System.out.println(result);
        System.out.println("----------------------------------------");
    }
}
