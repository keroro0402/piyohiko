package com.example.api.service;

import com.example.api.controller.TestGlobalExceptionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// テスト対象として、ハンドラーと、この下で定義するダミーコントローラーを指定
// GlobalExceptionHandler を使うコントローラがないので、これを使うためのコントローラを作成して使う
@WebMvcTest(controllers = {
        TestGlobalExceptionController.class,
        com.example.api.exception.GlobalExceptionHandler.class
}, excludeAutoConfiguration = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
})
public class GlobalExceptionHandlerTest {
    @Autowired
    private MockMvc mockMvc;

    @org.springframework.test.context.bean.override.mockito.MockitoBean
    private com.example.api.repository.UserRepository userRepository;

    @org.springframework.test.context.bean.override.mockito.MockitoBean
    private com.example.api.security.JwtUtil jwtUtil;


    /* 本番の網（ハンドラー）が、Formに書かれたエラーメッセージを『1文字も変えずに、全員分、綺麗にNuxt.jsに届ける』という仕事を正しく全うしているかを検証するテスト
    * TestGlobalExceptionController の throw400ValidationException を呼び出し、
    * 擬似的に発生させた MethodArgumentNotValidException が共通ハンドラー（handleValidationException）で正しく処理されるかを検証している。
    * ダミー画面（TestGlobalExceptionController）が仕込んだエラーメッセージを横取りするパターンで検証
    * 【このテストの流れ】
    * テスト（MockMvc） が /test/exception/400Validation を叩く。　→ ダミー画面（TestGlobalExceptionController） が、bindingResult.addError(...) を使って、
    * 本番のFormメッセージのフリをした「必須」「形式」「文字数」の3つの偽装エラーを箱に詰めて、本番の網（GlobalExceptionHandler）に向かって投げる。
    * 　→　本番の網 が起動し、箱の中から3つのメッセージを stream().map().toList() で横取り（抽出）して、
    * 新しいリストに詰め替えて Nuxt.js（テスト）に返す。 → 自動検査官（andExpect） が、戻ってきたリストの [0]、[1]、[2] 番目を上から順番にチェックして、
    * 全部一致したら Green（合格） にする。
    *  */
    @Test
    // バリデーションエラー時に共通ハンドラーが400を返すこと
    public void test_throw400ValidationException() throws Exception {
        mockMvc.perform(get("/test/exception/400Validation"))
                .andExpect(status().isBadRequest())// 400ステータスになること
                .andExpect(jsonPath("$.message[0]").value("400、リクエストが必須チェックバリデーションに弾かれました"))
                .andExpect(jsonPath("$.message[1]").value("400、リクエストが形式バリデーションに弾かれました"))
                .andExpect(jsonPath("$.message[2]").value("400、リクエストが文字数バリデーションに弾かれました"));
    }

    /* 本番の網（ハンドラー）が、リクエストのJSON破損を検知した際に自分で固定の「JSONの形式が不正です」というエラーメッセージを生成して、Nuxt.jsに届ける』という仕事を正しく全うしているかを検証するテスト
     * TestGlobalExceptionController の throw400BadRequestException を呼び出し、共通ハンドラー（handleJsonParseException）が400を返すかを検証している。
     *　エラーに対して、自作した文字をDTOに詰めて返すパターンで検証
     * 【このテストの流れ】
     * テスト（MockMvc） が /test/exception/400BadRequest を叩く。　→ ダミー画面（TestGlobalExceptionController） が、不正JSONを作って、本番の網（GlobalExceptionHandler）に投げる。
     * → 本番の網 が起動し、エラーをキャッチして、網自身が固定で用意している「JSONの形式が不正です」というエラーメッセージを新しく生成し、箱（DTO）に詰め替えて Nuxt.js（テスト）に返す。
     * → 自動検査官（andExpect） が、戻ってきた中身と自身が期待する内容とチェックして、全部一致したら Green（合格） にする。
     *  */
    @Test
    // JSON破損時に共通ハンドラーが400を返すこと
    public void test_throw400BadRequestException() throws Exception {
        mockMvc.perform(get("/test/exception/400BadRequest"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[0]").value("JSONの形式が不正です"));
    }


    /* 本番の網（ハンドラー）が、リクエストが受けれない際に自分で固定の「指定されたAPIが存在しません」というエラーメッセージを生成して、Nuxt.jsに届ける』という仕事を正しく全うしているかを検証するテスト
     * TestGlobalExceptionController の throw404NotFoundException を呼び出し、共通ハンドラー（handleNotFoundException）が404を返すかを検証している。
     *　エラーに対して、自作した文字やステータスをDTOに詰めて返すパターンで検証
     * 【このテストの流れ】
     * テスト（MockMvc） が /test/exception/404NotFound を叩く。　→ ダミー画面（TestGlobalExceptionController） が、例外を作成して本番の網（GlobalExceptionHandler）に投げる。
     * → 本番の網 が起動し、エラーをキャッチして、網自身が固定で用意しているエラーコードや「指定されたAPIが存在しません」というエラーメッセージを、箱（DTO）に詰め替えて Nuxt.js（テスト）に返す。
     * → 自動検査官（andExpect） が、戻ってきた中身と自身が期待する内容とチェックして、全部一致したら Green（合格） にする。
     *  */
    @Test
    // サーバーエラー時に共通ハンドラーが404を返すこと
    public void test_throw404NotFoundException() throws Exception {
        mockMvc.perform(get("/test/exception/404NotFound"))
                .andExpect(status().isNotFound()) // 404ステータスになること
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message[0]").value("リクエストされたAPIが存在しません"));
    }



    /* 本番の網（ハンドラー）が、リクエストが受けれない際に自分で固定の「システムエラーが発生しました。管理者にお問い合わせください。」というエラーメッセージを生成して、Nuxt.jsに届ける』という仕事を正しく全うしているかを検証するテスト
     * TestGlobalExceptionController の throw500ServerErrorException を呼び出し、共通ハンドラー（handleAllException）が500を返すかを検証している。
     *　エラーに対して、自作した文字やステータスをDTOに詰めて返すパターンで検証
     * 【このテストの流れ】
     * テスト（MockMvc） が /test/exception/500ServerError を叩く。　→ ダミー画面（TestGlobalExceptionController） が、例外を作成して本番の網（GlobalExceptionHandler）に投げる。
     * → 本番の網 が起動し、エラーをキャッチして、網自身が固定で用意しているエラーコードや「システムエラーが発生しました。管理者にお問い合わせください。」というエラーメッセージを、箱（DTO）に詰め替えて Nuxt.js（テスト）に返す。
     * → 自動検査官（andExpect） が、戻ってきた中身と自身が期待する内容とチェックして、全部一致したら Green（合格） にする。
     *  */
    @Test
    // サーバーエラー時に共通ハンドラーが500を返すこと
    public void test_throw500ServerErrorException() throws Exception {
        mockMvc.perform(get("/test/exception/500ServerError"))
                .andExpect(status().isInternalServerError()) // 500ステータスになること
                .andExpect(jsonPath("$.message[0]").value("システムエラーが発生しました。管理者にお問い合わせください。"));
    }
}