package com.example.springboottest1.controller;

import com.example.springboottest1.entity.ZyPeople;
import com.example.springboottest1.service.ZyPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 类UserLoginController的实现描述：TODO 类实现描述 
 * @author Administrator 2019/3/25 15:27
 */

@Controller
@RequestMapping(value = { "/zy"})
public class ZyPeopleController {

    /**
     * 注入service
     */
    @Autowired
    private ZyPeopleService zyPeopleService;


    /**
     * 注册新用户
     * @return 注册结果
     */
    @ResponseBody
    @RequestMapping(value = {"/getZyPeople"})
    public String getZyPeople(String sfzh){
        ZyPeople zyPeople = zyPeopleService.getZyPeople(sfzh);
        return "ok";
    }

    @ResponseBody
    @RequestMapping(value = {"/processPhoto"})
    public String processPhoto(){
        try {
            zyPeopleService.processSinglePhoto();
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
        return "ok";
    }

    @ResponseBody
    @RequestMapping(value = {"/insertPeople"})
    public String insertPeople(){
        try {
            zyPeopleService.insertZyczPeople();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
