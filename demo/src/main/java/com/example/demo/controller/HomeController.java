package com.example.demo.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 메인
    @GetMapping("/")
    public String index(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/user/login_personal";
        }
        return "user/main_personal";
    }

    // 시큐리티 확인
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
