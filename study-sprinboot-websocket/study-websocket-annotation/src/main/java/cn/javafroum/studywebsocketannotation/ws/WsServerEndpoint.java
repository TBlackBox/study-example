package cn.javafroum.studywebsocketannotation.ws;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@ServerEndpoint("/ws")
@Component
public class WsServerEndpoint {

	//存储在线用户的session
    private static Map<String, Session> onlineUserCache = new ConcurrentHashMap<String, Session>();

    //连接成功
    //@OnOpen 当 websocket 建立连接成功后会触发这个注解修饰的方法，注意它有一个 Session 参数
    @OnOpen
    public void onOpen(Session session) {
    	System.out.println("sessionId:" + session.getId());
        System.out.println("连接成功");
    }

    //连接关闭
    //@OnClose 当 websocket 建立的连接断开后会触发这个注解修饰的方法，注意它有一个 Session 参数
    @OnClose
    public void onClose(Session session) {
    	System.out.println("sessionId:" + session.getId());
        System.out.println("连接关闭");
    }

    //接收到消息
    //当客户端发送消息到服务端时，会触发这个注解修改的方法，它有一个 String 入参表明客户端传入的值
    @OnMessage
    public String onMsg(Session session,String text) throws IOException {
    	System.out.println("sessionId:" + session.getId());
    	//这是另外一种方式发送红数据  可以用于广播推送等功能
    	//session.getBasicRemote().sendText("哈哈");
        return "servet 发送：" + text;
    }
}