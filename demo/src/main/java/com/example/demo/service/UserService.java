package com.example.demo.service;

import com.example.demo.dto.UserSignupDTO;
import com.example.demo.vo.UserVO;

import java.util.Optional;

public interface UserService {
    // 회원가입
    void registerPersonalUser(UserSignupDTO request);
    // 로그인
    UserVO loginPersonalUser(String email, String password);
    // 이메일로 사용자 조회
    Optional<UserVO> findByEmail(String username);
}
