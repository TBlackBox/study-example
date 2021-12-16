package cn.javafroum.netty.server;

import cn.javafroum.netty.server.handler.DiscardServerHandler;
import io.netty.channel.ChannelFuture;

public class StartupServer {
    public static void main(String[] args){
        ChannelFuture future = null;
        SocketServer server = null;
        try{
            server = new SocketServer();
            future = server.doAccept(5001,new DiscardServerHandler());
            System.out.println("socket 服务端开始运行！");
            // 关闭连接的。
            future.channel().closeFuture().sync();
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            if(null != future){
                try {
                    future.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(null != server){
                server.release();
            }
        }
    }
}
