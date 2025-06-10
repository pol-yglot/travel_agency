package com.example.demo.mapper.user;

import com.example.demo.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void insertPersonalUser(UserVO user); // DTO → VO 변환 후 이걸 받음
    int existsByEmail(String email);
    Optional<UserVO> findByEmail(String email);
}