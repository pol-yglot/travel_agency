package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /*@GetMapping("/debug/auth")
    @ResponseBody
    public String checkAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            return "인증 정보 없음 (Authentication is null)";
        }

        return "인증 여부: " + auth.isAuthenticated() +
                "<br>Principal: " + auth.getPrincipal() +
                "<br>Authorities: " + auth.getAuthorities() +
                "<br>유저명: " + auth.getName();
    }*/

    @GetMapping("/mypage_personal")
    public String personalMypage(@AuthenticationPrincipal User user, Model model) {
        if (user == null) {
            return "redirect:/login_personal";
        }

        // DB에서 사용자 정보 조회 (user.getUsername() = email)
        Optional<UserVO> userVO = userService.findByEmail(user.getUsername());

        if (userVO.isEmpty()) {
            return "redirect:/login_personal?error=user-not-found";
        }

        model.addAttribute("user", userVO.get()); // Optionnal에서 값 꺼내기

        return "user/mypage_personal";
    }

    @GetMapping("/main_personal")
    public String personalMain(@AuthenticationPrincipal User user) {
        if (user == null) {
            return "redirect:/login_personal";
        }
        return "user/main_personal";
    }


}