package cn.javafroum.studywebsocketannotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket 
//开启websocket  这样springboot才会去扫描相应的注解 
//这个注解可以配置在配置类里面
public class StudyWebsocketAnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyWebsocketAnnotationApplication.class, args);
	}
}
