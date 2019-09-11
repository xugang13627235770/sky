package com.example.springboottest1.mapper;

import com.example.springboottest1.entity.ZyczPeople;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ZyczMapper {
    //测试
    List<ZyczPeople> getZyczPeopleBysfzh(@Param("pidList") List<String> pidList);

    int insertZyczPeople(ZyczPeople zyczPeople);

    ZyczPeople getZyczPeople(String pid);
}