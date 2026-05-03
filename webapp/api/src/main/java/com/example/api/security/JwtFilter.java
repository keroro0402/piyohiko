package com.example.api.security;

import com.example.api.entity.User;
import com.example.api.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, // リクエストデータを取り出す用オブジェクト
            HttpServletResponse response, // レスポンスデータを設定する用オブジェクト
            FilterChain filterChain // チェックを次々に続けるためのオブジェクト
    )
            // サーバエラー（ServletException）と通信エラー（IOException）発生時にSpringにエラー処理を委ねる
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        System.out.println("★★Filter開始★★");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);

            try {
                String loginId = JwtUtil.getLoginIdFromToken(token);

                User user = userRepository.findByLoginId(loginId);

                List<SimpleGrantedAuthority> authorities  = List.of(new SimpleGrantedAuthority(user.getRole()));

                System.out.println("認証OK:" + loginId);
                System.out.println("認証OK:" + authorities);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        loginId,
                        null,
                        authorities
                );
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                System.out.println("認証失敗・・・");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        // 次へ進む
        filterChain.doFilter(request, response);

    }
}
