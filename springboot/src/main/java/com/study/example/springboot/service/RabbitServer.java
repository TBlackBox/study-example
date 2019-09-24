//package com.study.example.springboot.service;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RabbitServer {
//
//    //直接结算消息
//    //里面可以指定队列的名字等参数
//    @RabbitListener(queues="queuesName")
//    public void receive(User user){
//        //接受消息
//        System.out.println("接受到的 消息"+user);
//    }
//
//    //接受消息体
//    @RabbitListener(queues="queuesName")
//    public void receive(Message message){
//        System.out.println("消息体："+message);
//    }
//
//}
