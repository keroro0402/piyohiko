package com.example.api.service;

import com.example.api.dto.SendPasswordResetEmailRequestDto;
import com.example.api.entity.PasswordResetRequest;
import com.example.api.entity.User;
import com.example.api.repository.SendPasswordResetEmailRepository;
import com.example.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class SendPasswordResetEmailServiceImpl implements SendPasswordResetEmailService{

    private final UserRepository userRepository;
    private final SendPasswordResetEmailRepository sendPasswordResetEmailRepository;
    private final JavaMailSender mailSender;

    // application.ymlからシークレットコードの有効期限を注入
    @Value("${secret-code.expiration-time}")
    private Long secretCodeExpirationTimeMin;
    @Value("${app.reset-url}")
    private String resetUrlBase;


    @Override
    public void sendPasswordResetEmail(SendPasswordResetEmailRequestDto sendPasswordResetEmailRequestDto) {
        /*  */
        User user = userRepository.findByEmail(sendPasswordResetEmailRequestDto.getEmail());
        // user が null（メアドが登録されていない）なら即終了
        if(user == null ){
//       throw new UserNotFoundException(404);
            return;
        }

        // シークレットコード用に整数（乱数）を作成する
        SecureRandom random = new SecureRandom();
        int code = 100 + random.nextInt(900); // nextInt(900)は0〜899を返すので、100を足すと100〜999になる
        String secretCode = String.valueOf(code);

        // シークレットコードの有効期限を設定（引数の単位はmin）
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(secretCodeExpirationTimeMin);

        PasswordResetRequest passwordResetRequest = new PasswordResetRequest(
                user.getUserId(),
                secretCode,
                expiryDate
        );
        // ユーザ情報をDBに登録する
        sendPasswordResetEmailRepository.createPasswordResetRequest(passwordResetRequest);


        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sendPasswordResetEmailRequestDto.getEmail());
        message.setSubject("【重要】パスワード再設定のご案内");

        message.setText(
                user.getUserId() + "様\n\n" +
                        "いつもご利用いただきありがとうございます。\n\n" +
                        "シークレットコードは" + " " + secretCode + " " + "です。\n\n" +
                        "以下手順でパスワードの更新をお願いいたします。\n\n" +
                        "1 : パスワードの更新 画面の シークレットコード に" +
                        " " + secretCode + " " +
                        "を入力する\n" +
                        "2 : 新しいパスワード と 新しいパスワード（確認）を入力する\n" +
                        "3 : パスワードを更新 ボタンを押す\n\n" +
                        "※シークレットコードの有効期限はメール着後、30分間です。\n時間を過ぎた場合はお手数ですが、 パスワードの再設定 画面から再操作をお願いいたします。"
        );

        mailSender.send(message);

    }
}
