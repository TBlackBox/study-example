package hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMian {
    public static void main(String[] args) {
        //创建spring 的IOC 容器对象
        // ApplicationContext是一个接口
        //ClassPathXmlApplicationContext 表示在类路径下去读取文件
        //FileSystemXmlApplicationContext 表示从文件获取容器配置信息
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //从ioc 容器中获取bean的实例,通过id获取
        Hello hello = (Hello)context.getBean("hello");
        //这种方法可以获取，但要求IOC容器中必须只有一个该类型的bean
        // Hello hello = context.getBean(Hello.class);
        //获取熟悉并打印
        System.out.println(hello.getName());

        User user = (User)context.getBean("user");
        System.out.println(user.username);
    }
}
