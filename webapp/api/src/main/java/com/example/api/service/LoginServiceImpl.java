package com.example.api.service;

import com.example.api.dto.LoginResponseDto;
import com.example.api.dto.UserDto;
import com.example.api.entity.User;
import com.example.api.exception.LoginException;
import com.example.api.repository.UserRepository;
import com.example.api.security.JwUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
            ){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponseDto login(String loginId, String password) {

        User user = userRepository.findByLoginId(loginId);

        /*
        * passwordEncoder は入力された平文 password を Bcryptアルゴリズムでハッシュ化
        * 生成されたハッシュと DBに保存されているハッシュ (user.getPassword()) を照合
        * 一致すれば true、一致しなければ false を返す
        * */
        if(user == null || !passwordEncoder.matches(password, user.getPassword()) ){
            throw new LoginException("LOGIN_FAILED", "ログインidまたはパスワードが不正です");
        }

        String token = JwUtil.generateToken(user.getLoginId());

        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setLoginId(user.getLoginId());

        LoginResponseDto dto = new LoginResponseDto();
        dto.setUser(userDto);
        dto.setAccessToken(token);
        return dto;
    }
}
