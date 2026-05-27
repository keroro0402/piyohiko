package com.example.api.security;

import com.example.api.entity.User;
import com.example.api.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private static final String BEARER_PREFIX = "Bearer ";
    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, // リクエストデータを取り出す用オブジェクト
            HttpServletResponse response, // レスポンスデータを設定する用オブジェクト
            FilterChain filterChain // チェックを次々に続けるためのオブジェクト
    )
            // サーバエラー（ServletException）と通信エラー（IOException）発生時にSpringにエラー処理を委ねる
            throws ServletException, IOException {
        System.out.println("★★Filter開始★★");
        // リクエスト メソッド = OPTIONS なら無条件で次の処理に進む
        if(HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())){
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("★★プリフライト通過★★");
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION); // Header から Authorization を取り出す
                // Authorization が トークン を情報に持つ場合（null ではなく、Bearer から始まる）
        if(authHeader != null && authHeader.startsWith(BEARER_PREFIX)){
            // Authorization の先頭7文字(Bearer )を削除してトークンを取り出す
            String token = authHeader.substring(7);
            try {
                String loginId = jwtUtil.getLoginIdFromToken(token); // トークンを使用して loginId を取り出す
                User user = userRepository.findByLoginId(loginId); // ログインIDに合致するユーザを取得
                List<SimpleGrantedAuthority> authorities  = List.of(new SimpleGrantedAuthority(user.getRole())); // 権限の認可チェック（hasRoleなど）ができるように、権限チェック専用のクラスに入れ、リストに格納する　
                System.out.println("認証OK:" + loginId);
                System.out.println("認証OK:" + authorities);
                // ユーザ情報を確定させる
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        loginId, // 1. 「誰が」アクセスしてきたか（Principal）
                        null, // 2. 「どんな証拠（パスワード）」で認証したか（Credentials）
                        authorities // 3. 「どんな権限」を持っているか（Authorities）
                );
                // 確定したユーザ情報をセキュリティシステムに登録して、認証システム本体(JwtFilter)を完成させる
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                log.error("認証処理に失敗しました", e);
                // （マジックナンバーになるので一般的なフィールド参照で）401を返す
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        // 次へ進む
        filterChain.doFilter(request, response);

    }
}
