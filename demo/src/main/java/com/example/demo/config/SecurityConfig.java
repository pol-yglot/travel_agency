package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                authorizationManagerRequestMatcherRegistry.requestMatchers("/").permitAll() // 메인 페이지는 인증 없이 접근 허용
                    .anyRequest().permitAll(); // 그 외의 요청은 인증 필요
            });
        return http.build();
    }
}
