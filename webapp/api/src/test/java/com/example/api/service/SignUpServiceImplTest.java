package com.example.api.service;

import com.example.api.dto.LoginResponseDto;
import com.example.api.dto.SignUpRequestDto;
import com.example.api.dto.SignUpResponseDto;
import com.example.api.entity.User;
import com.example.api.exception.DuplicateUserException;
import com.example.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/* ① テスト対象の実装クラスを指定 */
@SpringBootTest(classes = SignUpServiceImpl.class)
public class SignUpServiceImplTest {

    // テストで不変の値をフィールドに定義（テストで必須のフィールドのみ）
    private static final String TEST_PASSWORD = "test_register"; // スタブパスワード
    private static final String TEST_SECURITY_PHRASE_FOR_ADMIN = "YES_ADMIN"; // スタブADMIN用秘密の言葉
    private static final String TEST_SECURITY_PHRASE_FOR_USER = "udon"; // スタブUSER用秘密の言葉
    private static final String TEST_SUCCESS_MESSAGE = "新規登録が成功しました";
/* ② テスト対象をインターフェース型で受け取る */
    @Autowired
    private SignUpService signUpService;

/* ③ @MockitoBean で SignUpServiceImpl が動くために必要なモックオブジェクトを定義 */
    @MockitoBean
    private UserRepository userRepository;
    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void まずは動くか確認() {
        System.out.println("新規登録テストの部屋が立ち上がりました！");
    }

    // 正常系
    @Test
    public void 正しいメアドとパスワード_ROLE_ADMINで新規登録が成功すること() {
        /* 1. 【準備】本番ロジックが必要とするデータを作る */
        SignUpRequestDto dummyRequestDto = new SignUpRequestDto();
        dummyRequestDto.setEmail("admin@admin.com");
        dummyRequestDto.setPassword(TEST_PASSWORD);
        dummyRequestDto.setSecurityPhrase(TEST_SECURITY_PHRASE_FOR_ADMIN);

        // 2. MockitoBeanで作ったモックオブジェクトに組まれたメソッドに仮の引数をいれて実行させる
        // 「DBから検索されなかったら、null（重複なし）を返しなさい」と命令
        when(userRepository.findByEmail(dummyRequestDto.getEmail())).thenReturn(null);
        // 💡 「画面からの生パスワード」が渡されたら、「dummy_hashed_pw」という文字を返しなさい、と命令
        when(passwordEncoder.encode(TEST_PASSWORD)).thenReturn("dummy_hashed_pw");

        // 3. 【実行】完成した実験室で、本番のsignUpメソッドを外から呼び出す
        SignUpResponseDto result = signUpService.signUp(dummyRequestDto);

        // 4. 【検証】返ってきたDTOの中身が、期待通りになっているかチェックする
        assertNotNull(result); // ちゃんとDTOが返ってきていること
        assertEquals(TEST_SUCCESS_MESSAGE, result.getMessage()); // 登録成功メッセージが一致すること

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class); // signUpの実行結果をテスト側で取得するための記述
        verify(userRepository, times(1)).createUser(userCaptor.capture()); //

        User capturedUser = userCaptor.getValue();

        System.out.println("----------------------------------------");
        System.out.println("テスト成功！");
        System.out.println("message: " + result.getMessage());
        System.out.println("loginId: " + capturedUser.getEmail());
        System.out.println("password: " + capturedUser.getPassword());
        System.out.println("role: " + capturedUser.getRole());
        System.out.println("----------------------------------------");
    }

    @Test
    public void 正しいメアドとパスワード_ROLE_USERで新規登録が成功すること() {
        /* 1. 【準備】本番ロジックが必要とするデータ（ダミーのユーザー）を作る */
        SignUpRequestDto dummyRequestDto = new SignUpRequestDto();
        dummyRequestDto.setEmail("user@user.com");
        dummyRequestDto.setPassword(TEST_PASSWORD);
        dummyRequestDto.setSecurityPhrase(TEST_SECURITY_PHRASE_FOR_USER);

        // 2. MockitoBeanで作ったモックオブジェクトに組まれたメソッドに仮の引数をいれて実行させる
        // 「DBから検索されなかったら、null（重複なし）を返しなさい」と命令
        when(userRepository.findByEmail(dummyRequestDto.getEmail())).thenReturn(null);
        // 💡 「画面からの生パスワード」が渡されたら、「dummy_hashed_pw」という文字を返しなさい、と命令
        when(passwordEncoder.encode(TEST_PASSWORD)).thenReturn("dummy_hashed_pw");

        // 3. 【実行】完成した実験室で、本番のsignUpメソッドを外から呼び出す
        SignUpResponseDto result = signUpService.signUp(dummyRequestDto);

        // 4. 【検証】返ってきたDTOの中身が、期待通りになっているかチェックする
        assertNotNull(result); // ちゃんとDTOが返ってきていること
        assertEquals(TEST_SUCCESS_MESSAGE, result.getMessage()); // 登録成功メッセージが一致すること

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).createUser(userCaptor.capture());

        User capturedUser = userCaptor.getValue();

        System.out.println("----------------------------------------");
        System.out.println("テスト成功！");
        System.out.println("message: " + result.getMessage());
        System.out.println("loginId: " + capturedUser.getEmail());
        System.out.println("password: " + capturedUser.getPassword());
        System.out.println("role: " + capturedUser.getRole());
        System.out.println("----------------------------------------");
    }

    // 異常系
    @Test
    public void 重複データでリクエストが来たら登録を失敗すること(){
        // 1. 【準備】本番で入力されるデータのスタブを作成（メアドのみで他は不要）
        User user = new User();
        user.setEmail("testuser@testuser.com");
        // 【準備】signUpメソッドが必要なデータ（ダミーのデータ）を用意し、DTOを作る
        SignUpRequestDto dummySignUpRequestDto = new SignUpRequestDto();
        dummySignUpRequestDto.setEmail("testuser@testuser.com");

        // 2. MockitoBeanで作ったモックオブジェクトに組まれたメソッドに user をいれて実行させる
        // 「DBから検索できたら場合 user を返しなさい」と命令
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        // 3. 【実行】完成した実験室で、本番のsignUpメソッドを外から呼び出す
        DuplicateUserException exception = assertThrows(DuplicateUserException.class, () -> {
            signUpService.signUp(dummySignUpRequestDto);
        });

        // 4. 【検証】エラーの中身（エラーコード）が「SIGNUP_FAILED」と一致することを確認する
        // ※ JUnitの仕様上、画面に「SIGNUP_FAILED」が出るわけではないが、テストが落ちることなく終了できてるかを確認する
         assertEquals("SIGNUP_FAILED", exception.getErrorCode());
    }
}