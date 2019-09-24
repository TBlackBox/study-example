package com.study.example.springboot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TeskService {

    @Async
    public void asyncService(){
        System.out.println("异步任务处理数据");
    }

    @Scheduled(cron="0/4 * * * * *")
    public void timeService(){
        System.out.println("每4秒执行一次，执行定时器了！");
    }

}
