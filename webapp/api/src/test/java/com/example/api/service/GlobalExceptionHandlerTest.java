package com.example.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// テスト対象として、ハンドラーと、この下で定義するダミーコントローラーを指定
// GlobalExceptionHandler を使うコントローラがないので、これを使うためのコントローラを作成して使う
@WebMvcTest(controllers = {GlobalExceptionHandlerTest.TestController.class})
public class GlobalExceptionHandlerTest {
    @Autowired
    private MockMvc mockMvc;

    @RestController
    static class TestController {

        // 400：API のバリデーションに弾かれた時
        @GetMapping("/test/exception/400Validation") // テストなのでGetリクエストで十分のためGetを指定
        public void throw400ValidationException() throws MethodArgumentNotValidException {

            // ==========================================
            // ① 引数第1：methodParam（引数証明書）を作る
            // ==========================================
            // 本物の例外を new するために必要な「メソッドの引数証明書」を定義する。（Springのルールで必須）
            // ※「new Object(){}.getClass().getEnclosingMethod()」はJavaの裏技で、今実行しているこのメソッド自身の情報をその場で自動取得している。
            // ※第2引数の「-1」は、特定の引数ではなくメソッド全体でのエラーであることを表すSpringの決まり文句。
            org.springframework.core.MethodParameter methodParam =
                    new org.springframework.core.MethodParameter(
                            new Object(){}.getClass().getEnclosingMethod(), -1
                    );

            // ==========================================
            // ② 引数第2：bindingResult（エラーの器）を作る
            // ==========================================
            // 本番のExceptionHandler内（e.getBindingResult()）で使うため、バリデーションエラーをまとめる空の「器（オブジェクト）」を定義する。
            // ※テストなのでクラスは住所（パッケージ名）からの記載にして、別ファイルへのコピペを楽にしている。
            org.springframework.validation.BeanPropertyBindingResult bindingResult =
                    new org.springframework.validation.BeanPropertyBindingResult(new Object(), "dummyTarget");

            // 本番コード（.getFieldErrors()）がメッセージを抽出できるように、作成した器にダミーのエラー内容を1件セットする。
            // ※ここで第3引数に書いた文字列が、最終的にフロント（Nuxt.js）に届くエラーメッセージの本体になる。
            bindingResult.addError(new org.springframework.validation.FieldError(
                    "dummyTarget", "dummyField", "400、リクエストがバリデーションに弾かれました"
            ));

            // ==========================================
            // ③ 用意した「引数証明書」を「エラーの器」に入れて、本物の例外を生成して投げる
            // ==========================================
            throw new org.springframework.web.bind.MethodArgumentNotValidException(methodParam, bindingResult);
        }

        // 400：API のリクエストで JSON が破損、不正な時
        @GetMapping("/test/exception/400BadRequest")
        public void throw400BadRequestException() {

            // ==========================================
            // ① 空の通信ヘッダー、空のボディだけど、成立するテストデータ作成を作成
            // ==========================================
            org.springframework.http.HttpInputMessage dummyInputMessage = new org.springframework.http.HttpInputMessage() {

                // 💡 「通信のヘッダー情報」を返すメソッド。null禁止ルールをクリアしつつ、空っぽのヘッダーを返す。
                @Override
                @org.springframework.lang.NonNull
                public org.springframework.http.HttpHeaders getHeaders() {
                    return new org.springframework.http.HttpHeaders(); // 空っぽのヘッダー
                }
                // 「JSONデータの中身」を返すメソッド。null禁止ルールをクリアしつつ、中身が空っぽのデータ流を返す。
                @Override
                @org.springframework.lang.NonNull
                public java.io.InputStream getBody() throws java.io.IOException {
                    return new java.io.ByteArrayInputStream(new byte[0]); // 空っぽのデータ流
                }
            };

            // ==========================================
            // ② 第一引数にエラーメッセージ、第二引数にテストデータをセットして、本物の例外を生成して投げる
            // ==========================================
            throw new org.springframework.http.converter.HttpMessageNotReadableException(
                    "400、JSONに破損、不正があります", dummyInputMessage
            );
        }

        // 500：サーバが予期せぬトラブルでリクエストを取れない時
        @GetMapping("/test/exception/500ServerError")
        // この「Exception」クラスは、SpringではなくJava標準の「一番シンプルで大元の例外」なので、簡潔に完結
        public void throw500ServerErrorException() throws Exception {
            throw new Exception("500,サーバが予期せぬトラブルでリクエストを受け取れません");
        }

    }

}