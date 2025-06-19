package com.example.demo.config;

import com.example.demo.mapper.user.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                // 정적 리소스, 공개 페이지는 먼저 허용
                .requestMatchers(
                        "/", "/user/login**", "/signup**", "/signup_complete", "/css/**", "/js/**", "/images/**",
                        "/favicon.ico", "/site.webmanifest", "/api/**", "/flight/**"
                ).permitAll()
                // 권한 필요
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                // 나머지 모든 요청은 인증 필요
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login_personal")
                .successHandler((request, response, authentication) -> {
                    response.sendRedirect("/"); // 성공 후 무조건 홈으로
                })
                .failureUrl("/login_personal?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}