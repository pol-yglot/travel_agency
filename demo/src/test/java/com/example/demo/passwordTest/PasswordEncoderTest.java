package com.example.demo.passwordTest;

import com.example.demo.DemoApplication;
import com.example.demo.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(classes = {DemoApplication.class, SecurityConfig.class})
public class PasswordEncoderTest {
    @Autowired
    PasswordEncoder encoder;

    @Test
    void encode() {
        System.out.println(encoder.encode("1234"));
    }
}
