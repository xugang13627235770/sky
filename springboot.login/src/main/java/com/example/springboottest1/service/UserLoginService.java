package com.example.springboottest1.service;

import com.example.springboottest1.entity.User;
import com.example.springboottest1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类Userservice的实现描述：TODO 类实现描述 
 * @author Administrator 2019/3/25 15:20
 */
@Service
public class UserLoginService {

    /**
     * 注入dao
     */
    @Autowired
    private UserMapper usermapper;

    //用户登录
    public User userLogin(String username, String password){
        User user  = new User();
        user.setUsername(username);
        user.setPassword(password);
        User user1 = usermapper.userlogin(user);
        return user1;
    }

    //注册新用户
    public int adduser(String username,String password,int age){
        User user  = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        return usermapper.adduser(user);
        //return usermapper.adduser1(username,password,age);     //对应sql语句中的第二种注册方式
    }

}
