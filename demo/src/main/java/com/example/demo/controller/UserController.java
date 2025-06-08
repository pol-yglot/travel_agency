package com.example.demo.controller;

import com.example.demo.dto.UserSignupDTO;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @PostMapping("/login_personal")
    public String loginPersonal(@RequestParam String email,
                                @RequestParam String password,
                                RedirectAttributes redirectAttributes,
                                HttpSession session) {
        try {
            UserVO user = userService.loginPersonalUser(email, password);
            session.setAttribute("loginUser", user);
            log.info("로그인 성공: {}", user.getEmail());
            return "redirect:/user/main_personal"; // 로그인 성공 후 메인 페이지로 이동
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/user/login_personal";
        }
    }

    @GetMapping("/main_personal")
    public String personalMain(HttpSession session) {
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/user/login_personal"; // 로그인 안 된 경우 로그인 페이지로 리다이렉트
        }
        return "user/main_personal"; // 로그인 된 경우 메인 페이지로 이동
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
            return "redirect:/user/login_personal"; //메인으로 이동
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
