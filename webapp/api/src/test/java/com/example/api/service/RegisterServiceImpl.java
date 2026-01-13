package com.example.api.service;
// 実装クラス ：interface の「約束（メソッド名・戻り値・引数）」を実際にどう動くか具体的に書いたクラスのこと

import com.example.api.dto.TopicDto;
import com.example.api.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/* @Service
* ステレオタイプアノテーションの一種。これが付くとそのクラスは Bean になる
* */

/* Bean
* DI（依存性注入）をするために Spring が管理するインスタンス。
* */
@Service
/*
 * interface を使う（実装した）クラス RegisterServiceImpl を定義
 * implements キーワードで RegisterService インターフェイスに定義されたメソッドを必ず実装することを宣言している
 * 依存先は interface
 * */
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final RegisterRepository repository;
    /* @Override
     * interface に定義された register() メソッドを
     * このクラスで具体的に実装（処理内容の決定）する
     * */
    @Override
    public void register(TopicDto topic) {
        repository.add(topic);
    }
}
