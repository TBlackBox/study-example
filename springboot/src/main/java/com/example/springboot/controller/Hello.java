package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello {

//    @Autowired
//    TestMapper testMapper;

    @ResponseBody
    @RequestMapping("/test")
    public Object hello(){

        return "hello";
//        return testMapper.selectList();
    }
}
