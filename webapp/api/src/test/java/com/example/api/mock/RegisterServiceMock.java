package com.example.api.mock;
// 別パッケージにある別クラスを使用するために import する
import com.example.api.dto.TopicDto;
import com.example.api.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;

// 実装クラス ：interface の「約束（メソッド名・戻り値・引数）」を実際にどう動くか具体的に書いたクラスのこと

/* @Service
 * ステレオタイプアノテーションの一種。これが付くとそのクラスは Bean になる
 * */
//@Service
/*
 * interface を使う（実装した）クラス RegisterServiceMock を定義
 * implements キーワードで RegisterService インターフェイスに定義されたメソッドを必ず実装することを宣言している
 * 依存先は interface
 * */
@RequiredArgsConstructor
public class RegisterServiceMock implements OldRegisterService {
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