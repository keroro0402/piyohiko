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


    /* 本番の網（ハンドラー）が、Formに書かれたエラーメッセージを『1文字も変えずに、全員分、綺麗にNext.jsに届ける』という仕事を正しく全うしているかを検証するテスト */
    @Test
    public void test_400Validation_バリデーションエラー時に共通ハンドラーが400を返すこと() throws Exception {
        mockMvc.perform(get("/test/exception/400Validation"))
                .andExpect(status().isBadRequest())// 400ステータスになること
                .andExpect(jsonPath("$.message[0]").value("400、リクエストが必須チェックバリデーションに弾かれました"))
                .andExpect(jsonPath("$.message[1]").value("400、リクエストが形式バリデーションに弾かれました"))
                .andExpect(jsonPath("$.message[2]").value("400、リクエストが文字数バリデーションに弾かれました"));
    }

    @Test
    public void test_400BadRequest_JSON破損時に共通ハンドラーが400を返すこと() throws Exception {
        mockMvc.perform(get("/test/exception/400BadRequest"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[0]").value("JSONの形式が不正です"));
    }

    @Test
    public void test_500ServerError_サーバーエラー時に共通ハンドラーが500を返すこと() throws Exception {
        mockMvc.perform(get("/test/exception/500ServerError"))
                .andExpect(status().isInternalServerError()) // 500ステータスになること
                .andExpect(jsonPath("$.message[0]").value("システムエラーが発生しました。管理者にお問い合わせください。"));
    }
}