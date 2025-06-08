package com.example.demo.service;

import com.example.demo.dto.UserSignupDTO;
import com.example.demo.vo.UserVO;
import jakarta.servlet.http.HttpSession;

public interface UserService {
    // 회원가입
    void registerPersonalUser(UserSignupDTO request);
    // 로그인
    UserVO loginPersonalUser(String email, String password);
}
