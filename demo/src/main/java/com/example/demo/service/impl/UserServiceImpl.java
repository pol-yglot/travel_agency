package com.example.demo.service.impl;

import com.example.demo.dto.UserSignupDTO;
import com.example.demo.mapper.user.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerPersonalUser(UserSignupDTO dto) {
        int count = userMapper.existsByEmail(dto.getEmail());
        System.out.println("==== 중복 이메일 여부: " + count);

        if (count > 0) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        UserVO user = new UserVO();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setName(dto.getName());
        user.setPhone(dto.getPhone());
        user.setBirthDate(dto.getBirthDate());
        user.setPassportName(dto.getPassportName());
        user.setNationality(dto.getNationality());
        user.setMarketingAgree(dto.getMarketingAgree());
        user.setUserType("PERSONAL");

        userMapper.insertPersonalUser(user);
    }

    @Override
    public UserVO loginPersonalUser(String email, String password) {
        UserVO user = userMapper.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
        return user;
    }

}
