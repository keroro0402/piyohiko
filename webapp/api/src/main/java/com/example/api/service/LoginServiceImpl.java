package com.example.api.service;

import com.example.api.dto.LoginRequestDto;
import com.example.api.dto.LoginResponseDto;
import com.example.api.dto.TokenDto;
import com.example.api.dto.UserDto;
import com.example.api.entity.User;
import com.example.api.exception.LoginException;
import com.example.api.repository.UserRepository;
import com.example.api.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public LoginServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
            ){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByLoginId(loginRequestDto.getEmail());

        /*
        * passwordEncoder は入力された平文 password を Bcryptアルゴリズムでハッシュ化
        * 生成されたハッシュと DBに保存されているハッシュ (user.getPassword()) を照合
        * 一致すれば true、一致しなければ false を返す
        * */
        if(user == null || !passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword()) ){
            throw new LoginException("LOGIN_FAILED", "メールアドレスまたはパスワードが不正です");
        }
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole(), loginRequestDto.getExpiration());

        // レスポンスDTOのユーザ情報用フィールドに値をセットする
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        // レスポンスDTOのトークン用フィールドに値をセットする
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccessToken(token);
        tokenDto.setExpiration(loginRequestDto.getExpiration());
        // ユーザ情報とトークン用フィールドをレスポンスDTOにまとめる
        LoginResponseDto dto = new LoginResponseDto();
        dto.setUser(userDto);
        dto.setToken(tokenDto);
        // レスポンスDTOを返却
        return dto;
    }
}
