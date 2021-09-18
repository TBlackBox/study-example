package cn.javafroum.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/security")
    public String security(){
        return "安全测试";
    }

    @GetMapping("/all")
    public String all(){
        return "所有用户都能访问";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin用户访问";
    }

    @GetMapping("/normal")
    public String normal(){
        return "normal角色的能访问";
    }

    //@PermitAll 注解，等价于 #permitAll() 方法，所有用户可访问。
    //因为在SecurityConfig中，配置了 .anyRequest().authenticated() ，任何请求，访问的用户都需要经过认证。所以这里 @PermitAll 注解实际是不生效的。
    //也就是说，Java Config 配置的权限，和注解配置的权限，两者是叠加的。
    @PermitAll
    @GetMapping("/all1")
    public String all1() {
        return "所有用户都能访问";
    }

    @GetMapping("/home")
    public String home() {
        return "我是首页";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin1")
    public String admin1() {
        return "admin1用户访问";
    }

    //@PreAuthorize 注解，等价于 #access(String attribute) 方法，，当 Spring EL 表达式的执行结果为 true 时，可以访问。
    @PreAuthorize("hasRole('ROLE_NORMAL')")
    @GetMapping("/normal1")
    public String normal1() {
        return "normal1角色的能访问";
    }

    //还有一些其他的注解可以到官网学习

}
