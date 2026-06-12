package com.example.api.service;

import com.example.api.dto.ResetPasswordRequestDto;
import com.example.api.dto.ResetPasswordResponseDto;
import com.example.api.entity.ResetPassword;
import com.example.api.entity.User;
import com.example.api.exception.BusinessException;
import com.example.api.repository.ResetPasswordRepository;
import com.example.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ResetPasswordServiceImpl implements ResetPasswordService{

    private final ResetPasswordRepository resetPasswordRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResetPasswordResponseDto resetPassword(ResetPasswordRequestDto resetPasswordRequestDto) {

        String email = resetPasswordRequestDto.getEmail();
        String secretCode = resetPasswordRequestDto.getSecretCode();
        LocalDateTime localDateTime = java.time.LocalDateTime.now();

        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new BusinessException(400,
                    "USER_NOT_FOUND",
                    "パスワードを変更できませんでした\nメールアドレスが未登録か、" +
                    "シークレットコードが無効（期限切れなど）の可能性があります");
        }

        ResetPassword resetPassword = resetPasswordRepository
                .findByUserIdAndSecretCodeAndIsUsedFalseAndExpiryDateAfter(
                        user.getUserId(),
                        secretCode,
                        LocalDateTime.now()
                ).orElseThrow(() -> new BusinessException(400,
                        "INVALID_CODE",
                        "パスワードを変更できませんでした\nメールアドレスが未登録か、" +
                        "シークレットコードが無効（期限切れなど）の可能性があります"));

        String encodedPassword = passwordEncoder.encode(resetPasswordRequestDto.getPassword()); // 新パスワードをエンコード
        userRepository.passwordUpdate( user.getUserId(), encodedPassword); // t_user の password を更新
        resetPasswordRepository.updateIsUsedToTrue(resetPassword.id());  // t_password_reset_request の is_used を更新

        ResetPasswordResponseDto resetPasswordResponseDto = new ResetPasswordResponseDto();
        resetPasswordResponseDto.setMessage("PW更新を完了しました");

        return resetPasswordResponseDto;
    }
}
