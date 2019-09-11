package com.example.springboottest1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类HelloController的实现描述：TODO 类实现描述 
 * @author Administrator 2019/3/25 15:07
 */

@Controller
@RequestMapping(value = { "/hello"})
public class HelloController {

    @RequestMapping(value = {"/springboot"})
    public String hello(){
        return "HelloWord";
    }
}
