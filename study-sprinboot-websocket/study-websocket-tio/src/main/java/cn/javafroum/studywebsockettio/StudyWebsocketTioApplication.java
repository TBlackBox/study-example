package cn.javafroum.studywebsockettio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tio.websocket.starter.EnableTioWebSocketServer;

@SpringBootApplication
@EnableTioWebSocketServer  //开启tiowebsocket服务
public class StudyWebsocketTioApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyWebsocketTioApplication.class, args);
	}

}
