package cn.javafroum.mybatis.springboot2.controller;

import cn.javafroum.mybatis.springboot2.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MybatisTest {

    @Autowired
    LogMapper logMapper;

    @ResponseBody
    @RequestMapping("/hello")
    public Object hello(){

        return logMapper.selectLogList1();
    }
}
