package com.example.springboottest1.mapper;

import com.example.springboottest1.entity.MyPage;
import com.example.springboottest1.entity.ZyPeople;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ZyMapper {
    //测试
    ZyPeople getZyPeople(String sfzh);

    Integer getZyPeopleCount();

    List<ZyPeople> getAllZyPeople(MyPage myPage);
}