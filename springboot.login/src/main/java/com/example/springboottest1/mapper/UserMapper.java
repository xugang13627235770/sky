package com.example.springboottest1.mapper;

import com.example.springboottest1.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    //用户登录
    User userlogin(User user);

    //注册新用户(方式1)
    int adduser(User user);

    //注册新用户（方式2）
    int adduser1(User use);
}