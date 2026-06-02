package com.example.api.controller;

import com.example.api.repository.UserRepository;
import com.example.api.security.JwtUtil;
import com.example.api.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LoginController.class, excludeAutoConfiguration = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
})
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LoginService loginService;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Test
    public void ログインIDが空のとき400エラーになること() throws Exception {
        String badJson = """
            {
                "loginId": "",
                "password": "raw_password"
            }
            """;

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badJson))
                .andExpect(status().isBadRequest());
    }
}