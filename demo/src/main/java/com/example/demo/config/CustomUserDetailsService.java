package com.example.demo.config;

import com.example.demo.mapper.user.UserMapper;
import com.example.demo.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO user = userMapper.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        // null-safe 권한 처리
        String userType = user.getUserType();
        String role = "USER"; // 기본값
        if ("ADMIN".equals(userType)) {
            role = "ADMIN";
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword()) // BCrypt 암호화된 비밀번호
                .roles(role) // Spring Security에서 내부적으로 ROLE_ 접두어 붙음
                .build();
    }
}