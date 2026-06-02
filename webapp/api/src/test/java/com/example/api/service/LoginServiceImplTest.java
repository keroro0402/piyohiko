package com.example.api.service;

import com.example.api.dto.LoginRequestDto;
import com.example.api.dto.LoginResponseDto;
import com.example.api.entity.User;
import com.example.api.exception.LoginException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.api.repository.UserRepository;
import com.example.api.security.JwtUtil;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/* ① テスト対象の実装クラスを指定 */
@SpringBootTest(classes = LoginServiceImpl.class)
public class LoginServiceImplTest {

    // テストで不変の値をフィールドに定義
    private static final String TEST_LOGIN_ID = "user1";
    private static final String TEST_ROLE = "ROLE_USER";
    private static final String MOCKED_TOKEN = "mocked_jwt_token";
    private static final long TEST_EXPIRATION = 3600L;
/* ② テスト対象をインターフェース型で受け取る */
    @Autowired
    private LoginService loginService;

/* ③ @MockitoBean で LoginServiceImpl が動くために必要な3つのモックオブジェクトを定義 */
    @MockitoBean
    private UserRepository userRepository;
    @MockitoBean
    private PasswordEncoder passwordEncoder;
    @MockitoBean
    private JwtUtil jwtUtil;

    @Test
    public void まずは動くか確認() {
        System.out.println("ログインテストの部屋が立ち上がりました！");
    }

    // 正常系
    @Test
    public void 正しいIDとパスワードでログインが成功すること() {
        // 1. 【準備】本番ロジックが必要とするデータ（ダミーのユーザー）を作る
        User dummyUser = new User();
        dummyUser.setUserId(1); // DBに入っている（つもりの）ユーザID
        dummyUser.setLoginId(TEST_LOGIN_ID); // DBに入っている（つもりの）ログインID
        dummyUser.setPassword("hashed_password"); // DBに入っている（つもりの）ハッシュ化されたパスワード
        dummyUser.setRole(TEST_ROLE); // DBに入っている（つもりの）ロール

        // 【準備】logInメソッドが必要なデータ（ダミーのデータ）を用意し、DTOを作る
        LoginRequestDto dummyRequestDto = new LoginRequestDto();
        dummyRequestDto.setLoginId(TEST_LOGIN_ID);
        dummyRequestDto.setPassword("raw_password");
        dummyRequestDto.setExpiration(TEST_EXPIRATION);

        // 2. MockitoBeanで作ったモックオブジェクトに組まれたメソッドに仮の引数をいれて実行させる
        // 「DBから検索されたら、1. で作った dummyUser を返しなさい」と命令
        when(userRepository.findByLoginId(TEST_LOGIN_ID)).thenReturn(dummyUser);
        // 「パスワード照合されたら、一致した（true）と返しなさい」と命令
        when(passwordEncoder.matches("raw_password", "hashed_password")).thenReturn(true);
        // 「トークン生成を頼まれたら、MOCKED_TOKEN（mocked_jwt_token という文字）を返しなさい」と命令
        when(jwtUtil.generateToken(TEST_LOGIN_ID, TEST_ROLE, TEST_EXPIRATION)).thenReturn(MOCKED_TOKEN);

        // 3. 【実行】完成した実験室で、本番のloginメソッドを外から呼び出す！
        LoginResponseDto result = loginService.login(dummyRequestDto);

        // 4. 【検証】返ってきたDTOの中身が、期待通りになっているかチェックする
        assertNotNull(result); // ちゃんとDTOが返ってきていること
        assertEquals(MOCKED_TOKEN, result.getToken().getAccessToken()); // トークンが一致すること
        assertEquals(TEST_LOGIN_ID, result.getUser().getLoginId()); // ログインIDが一致すること

        System.out.println("----------------------------------------");
        System.out.println("テスト成功！トークン: " + result.getToken().getAccessToken());
        System.out.println("----------------------------------------");
    }

    // 異常系
    @Test
    public void 未登録ユーザでログインが失敗すること() {
        // 1. 【準備】本番ロジックが必要とするデータ（ダミーのユーザー）を作る
        // 未登録ユーザ を想定するのでダミーユーザは作成しない

        // 【準備】logInメソッドが必要なデータ（ダミーのデータ）を用意し、DTOを作る
        LoginRequestDto dummyRequestDto = new LoginRequestDto();
        dummyRequestDto.setLoginId(TEST_LOGIN_ID);
        dummyRequestDto.setPassword("raw_password");
        dummyRequestDto.setExpiration(TEST_EXPIRATION);

        // 2. MockitoBeanで作ったモックオブジェクトに組まれたメソッドに null をいれて実行させる
        // 「DBから検索できない場合 null を返しなさい」と命令
        when(userRepository.findByLoginId(TEST_LOGIN_ID)).thenReturn(null);

        // 3. 【実行】完成した実験室で、本番のloginメソッドを外から呼び出す！
        LoginException exception = assertThrows(LoginException.class, () -> {
            loginService.login(dummyRequestDto);
        });
        // 4. 【検証】返り値が期待通りに null なっているかチェックする
        assertEquals("LOGIN_FAILED", exception.getErrorCode());
    }

}