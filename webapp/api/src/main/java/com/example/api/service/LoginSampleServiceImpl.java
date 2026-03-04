package com.example.api.service;

import com.example.api.entity.User;
import com.example.api.repository.UserSampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginSampleServiceImpl implements LoginSampleService {
    /*
     * Spring が LoginServiceImpl をインスタンス化する際に、
     * UserRepository 型のBeanをコンストラクタ経由で注入する
     */
    private final UserSampleRepository userRepository;

    /*
    * @Override
    * 親の LoginService インターフェイスに定義された login メソッドがあることをここで実装していることを示す
    * */
    @Override
    public User login(String loginId, String password) {
        /*UserRepository の findByLoginId メソッドで loginId に対応した User を取得*/
        User user = userRepository.findByLoginId(loginId);
        /*
        * 入力PW（引数）とDBのPW（user.getPassword()）が等しいかどうかを判定
        * 等しくない = ログイン失敗の例外を返却
        * */
        if(!user.getPassword().equals(password)){
            throw new RuntimeException("ログイン失敗");
        }
        /*等しい = 認証OKなので User 情報を返却*/
        return user;
    }
}
