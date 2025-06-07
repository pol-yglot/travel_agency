package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
        return "index";
    }

    /*
    // DB 연결 확인
    @GetMapping("/db-check")
    public String checkDatabase() {
        String now = jdbcTemplate.queryForObject("SELECT NOW()", String.class);
        return "DB 연결됨: " + now;
    }*/
}
