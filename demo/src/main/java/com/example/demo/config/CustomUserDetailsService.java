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
        Optional<UserVO> user = Optional.ofNullable(userMapper.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.map(UserVO::getEmail).orElseThrow())
                .password(user.map(UserVO::getPassword).orElseThrow()) // 반드시 암호화된 상태여야 함!
                .roles("USER") // 또는 user.getRole()로 동적 처리
                .build();
    }
}