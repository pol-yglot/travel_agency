package com.example.demo.MyBatis;

import com.example.demo.mapper.user.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class MybatisTest {

    private final UserMapper userMapper;

    public MybatisTest(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Test
    void checkEmailQuery() {
        int count = userMapper.existsByEmail("test@example.com");
        System.out.println("중복 count: " + count);
    }
}