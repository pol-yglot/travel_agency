package com.example.demo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UserSignupDTO {
    private String email;
    private String password;
    private String name;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String passportName;
    private String nationality;
    private Boolean marketingAgree;
}
