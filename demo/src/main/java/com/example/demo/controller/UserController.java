package com.example.demo.controller;

import com.example.demo.dto.UserSignupDTO;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login_personal")
    public String personalLogin() {
        return "user/login_personal";
    }

    @GetMapping("/logout_personal")
    public String logoutPersonal() {
        // 로그아웃 처리는 Spring Security가 담당하므로 별도 세션 처리 불필요
        return "redirect:/user/login_personal?logout";
    }

    @GetMapping("/mypage_personal")
    public String personalMypage(@AuthenticationPrincipal User user) {
        if (user == null) {
            return "redirect:/user/login_personal";
        }
        return "user/mypage_personal";
    }

    @GetMapping("/main_personal")
    public String personalMain(@AuthenticationPrincipal User user) {
        if (user == null) {
            return "redirect:/user/login_personal";
        }
        return "user/main_personal";
    }

    @GetMapping("/signup_personal")
    public String signupPersonalForm() {
        return "user/signup_personal";
    }

    @PostMapping("/signup_personal")
    public String signupPersonal(@ModelAttribute UserSignupDTO request, RedirectAttributes redirectAttributes) {
        try {
            userService.registerPersonalUser(request);
            redirectAttributes.addAttribute("signup", "success");
            return "redirect:/user/login_personal";
        } catch (Exception e) {
            log.error("회원가입 중 오류 발생: {}", e.getMessage());
            if (e.getMessage().contains("이미 가입된 이메일입니다.")) {
                redirectAttributes.addFlashAttribute("errorMessage", "이미 가입된 이메일입니다.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "회원가입 중 오류가 발생하였습니다. \n 다시 시도해주세요.");
            }
            return "redirect:/user/signup_personal";
        }
    }
}