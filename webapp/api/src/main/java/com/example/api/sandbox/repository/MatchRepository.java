package com.example.api.sandbox.repository;
import com.example.api.sandbox.dto.MatchDto;
import java.time.LocalDate;


public interface MatchRepository {
    /* MatchRepogitoryImplで使用する抽象メソッドを定義*/
    void add(MatchDto dto); // DBにdtoを登録するメソッド

    /*インフラ層では重複登録が「ある」「ない」を調査するメソッドを定義する
    *実際に登録するしないの判断をするメソッドではないのがポイント
    * リポジトリに書く処理はその結果で、サービスは仕事を進めれるかを念頭にするとよい。
    * 今回でいえばDBに登録が「ある」「ない」の事実だけを出せばよい
    * */
    boolean existByDateAndPlace(LocalDate date, String place);
}
