package com.example.api.service;

public interface RegisterService {
    /*
    * RegisterService インターフェイスは
    * コントローラと実装する側クラス（RegisterServiceImpl、RegisterServiceMock）との間に入る役目で
    * 処理の内容や結果などは記述しない抽象メソッドのみを定義
    * */
    String register();
}
/* コントローラ、interface、実装クラスの相関図
[ Controller ]
      |
      |  (呼ぶ)
      v
[ RegisterService ]   ← interface（約束）
      ^
      |  (実装する)
      |
[ RegisterServiceImpl ] / [ RegisterServiceMock ]
* */