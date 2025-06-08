package com.example.demo.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserVO {
    private Long userId;
    private String email;
    private String password;
    private String name;
    private String phone;
    private LocalDate birthDate;
    private String passportName;
    private String nationality;
    private Boolean marketingAgree;
    private String userType; // PERSONAL or CORPORATE
}