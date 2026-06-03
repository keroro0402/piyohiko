package com.example.api.service;

import com.example.api.dto.SignUpRequestDto;
import com.example.api.dto.SignUpResponseDto;
import com.example.api.entity.User;
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
    public void 正しいIDとパスワード_ROLE_ADMINで新規登録が成功すること() {
        /* 1. 【準備】本番ロジックが必要とするデータを作る */
        SignUpRequestDto dummyRequestDto = new SignUpRequestDto();
        dummyRequestDto.setLoginId("admin");
        dummyRequestDto.setPassword(TEST_PASSWORD);
        dummyRequestDto.setSecurityPhrase(TEST_SECURITY_PHRASE_FOR_ADMIN);

        // 2. MockitoBeanで作ったモックオブジェクトに組まれたメソッドに仮の引数をいれて実行させる
        // 「DBから検索されなかったら、null（重複なし）を返しなさい」と命令
        when(userRepository.findByLoginId(dummyRequestDto.getLoginId())).thenReturn(null);
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
        System.out.println("loginId: " + capturedUser.getLoginId());
        System.out.println("password: " + capturedUser.getPassword());
        System.out.println("role: " + capturedUser.getRole());
        System.out.println("----------------------------------------");
    }

    @Test
    public void 正しいIDとパスワード_ROLE_USERで新規登録が成功すること() {
        /* 1. 【準備】本番ロジックが必要とするデータ（ダミーのユーザー）を作る */
        SignUpRequestDto dummyRequestDto = new SignUpRequestDto();
        dummyRequestDto.setLoginId("user");
        dummyRequestDto.setPassword(TEST_PASSWORD);
        dummyRequestDto.setSecurityPhrase(TEST_SECURITY_PHRASE_FOR_USER);

        // 2. MockitoBeanで作ったモックオブジェクトに組まれたメソッドに仮の引数をいれて実行させる
        // 「DBから検索されなかったら、null（重複なし）を返しなさい」と命令
        when(userRepository.findByLoginId(dummyRequestDto.getLoginId())).thenReturn(null);
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
        System.out.println("loginId: " + capturedUser.getLoginId());
        System.out.println("password: " + capturedUser.getPassword());
        System.out.println("role: " + capturedUser.getRole());
        System.out.println("----------------------------------------");
    }


//    // 異常系
//    @Test
//    public void 未登録ユーザでログインが失敗すること() {
//        // 1. 【準備】本番ロジックが必要とするデータ（ダミーのユーザー）を作る
//        // 未登録ユーザ を想定するのでダミーユーザは作成しない
//
//        // 【準備】logInメソッドが必要なデータ（ダミーのデータ）を用意し、DTOを作る
//        LoginRequestDto dummyRequestDto = new LoginRequestDto();
//        dummyRequestDto.setLoginId(TEST_LOGIN_ID);
//        dummyRequestDto.setPassword("raw_password");
//        dummyRequestDto.setExpiration(TEST_EXPIRATION);
//
//        // 2. MockitoBeanで作ったモックオブジェクトに組まれたメソッドに null をいれて実行させる
//        // 「DBから検索できない場合 null を返しなさい」と命令
//        when(userRepository.findByLoginId(TEST_LOGIN_ID)).thenReturn(null);
//
//        // 3. 【実行】完成した実験室で、本番のloginメソッドを外から呼び出す！
//        LoginException exception = assertThrows(LoginException.class, () -> {
//            loginService.login(dummyRequestDto);
//        });
//        // 4. 【検証】返り値が期待通りに null なっているかチェックする
//        assertEquals("LOGIN_FAILED", exception.getErrorCode());
//    }
//
//    @Test
//    public void ログインIDに紐づかないパスワードでログインが失敗すること() {
//        // 1. 【準備】本番ロジックが必要とするデータ（ダミーのユーザー）を作る
//        User dummyUser = new User();
//        dummyUser.setUserId(1); // DBに入っている（つもりの）ユーザID
//        dummyUser.setLoginId(TEST_LOGIN_ID); // DBに入っている（つもりの）ログインID
//        dummyUser.setPassword("hashed_password"); // DBに入っている（つもりの）ハッシュ化されたパスワード
//        dummyUser.setRole(TEST_ROLE); // DBに入っている（つもりの）ロール
//
//        // 【準備】logInメソッドが必要なデータ（ダミーのデータ）を用意し、DTOを作る
//        LoginRequestDto dummyRequestDto = new LoginRequestDto();
//        dummyRequestDto.setLoginId(TEST_LOGIN_ID);
//        dummyRequestDto.setPassword("raw_password");
//        dummyRequestDto.setExpiration(TEST_EXPIRATION);
//
//        // 2. MockitoBeanで作ったモックオブジェクトに組まれたメソッドに仮の引数をいれて実行させる
//        // 「DBから検索されたら、1. で作った dummyUser を返しなさい」と命令
//        when(userRepository.findByLoginId(TEST_LOGIN_ID)).thenReturn(dummyUser);
//        // 「パスワード照合されたら、一致しない（false）と返しなさい」と命令
//        when(passwordEncoder.matches("raw_password", "hashed_password")).thenReturn(false);
//
//        // 3. 【実行】完成した実験室で、本番のloginメソッドを外から呼び出す
//        LoginException exception = assertThrows(LoginException.class, () -> {
//            loginService.login(dummyRequestDto);
//        });
//
//        // 4. 【検証】返り値が期待通りに null なっているかチェックする
//        assertEquals("LOGIN_FAILED", exception.getErrorCode());
//    }

}