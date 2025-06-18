package com.example.demo.controller;

import com.example.demo.dto.UserSignupDTO;
import com.example.demo.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController implements ErrorController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final UserService userService;

    // 메인
    @GetMapping("/")
    public String index(){
        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/login_personal";
        }*/
        return "index";
    }

    // 로그인
    @GetMapping("/login_personal")
    public String personalLogin() {
        return "login_personal";
    }

    // 회원가입
    @GetMapping("/signup_personal")
    public String signupPersonalForm() {
        return "signup_personal";
    }

    @PostMapping("/signup_personal")
    public String signupPersonal(@ModelAttribute UserSignupDTO request, RedirectAttributes redirectAttributes) {
        try {
            userService.registerPersonalUser(request);
            return "signup_complete";
        } catch (Exception e) {
            log.error("회원가입 중 오류 발생: {}", e.getMessage());
            if (e.getMessage().contains("이미 가입된 이메일입니다.")) {
                redirectAttributes.addFlashAttribute("errorMessage", "이미 가입된 이메일입니다.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "회원가입 중 오류가 발생하였습니다. \n 다시 시도해주세요.");
            }
            return "redirect:/signup_personal";
        }
    }

    // 에러 공통 페이지
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int code = Integer.parseInt(status.toString());
            if (code == 404) return "common/error/404";
            if (code == 500) return "common/error/500";
        }
        return "common/error/default";
    }

    // 시큐리티 적용 확인
    @GetMapping("/security_check")
    public String securityCheck() {
        return "security_check";
    }

    // 빈 중복등록 확인
    /*@Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void checkUserDetailsServiceBeans() {
        String[] beanNames = applicationContext.getBeanNamesForType(UserDetailsService.class);
        if (beanNames.length > 1) {
            System.out.println("중복된 UserDetailsService 빈 발견:");
            for (String beanName : beanNames) {
                System.out.println("- " + beanName);
            }
        } else {
            System.out.println("UserDetailsService 빈은 하나만 등록되어 있습니다.");
        }
    }*/

    /*
    // DB 연결 확인
    @GetMapping("/db-check")
    public String checkDatabase() {
        String now = jdbcTemplate.queryForObject("SELECT NOW()", String.class);
        return "DB 연결됨: " + now;
    }*/
}
