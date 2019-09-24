package com.study.example.springboot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


//@EnableRabbit  //开启基于注解的RabbitMq模式
//@EnableAsync //开启异步注解
@EnableScheduling  //开启基于注解的定时任务
@SpringBootApplication
public class SpringbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
