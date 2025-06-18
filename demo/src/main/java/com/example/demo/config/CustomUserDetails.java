package com.example.demo.config;

import com.example.demo.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    // 유저 vo
    private final UserVO userVO;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // PERSONAL, CORPORATE 모두 ROLE_USER로 매핑
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return userVO.getPassword(); // 암호화된 비밀번호
    }

    @Override
    public String getUsername() {
        return userVO.getEmail(); // 이메일을 로그인 ID로 사용
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

}
