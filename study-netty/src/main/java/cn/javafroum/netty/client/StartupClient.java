package cn.javafroum.netty.client;

import cn.javafroum.netty.client.handler.ClientHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class StartupClient {
    public static void main(String[] args) {
        SocketClient client = null;
        ChannelFuture future = null;
        try{
            client = new SocketClient();
            future = client.doRequest("101.37.172.188", 9090, new ClientHandler());

            Scanner s = null;
            while(true){
                s = new Scanner(System.in);
                System.out.print("enter message send to server (enter 'exit' for close client) > ");
                String line = s.nextLine();
                if("exit".equals(line)){
                    // addListener - 增加监听，当某条件满足的时候，触发监听器。
                    // ChannelFutureListener.CLOSE - 关闭监听器，代表ChannelFuture执行返回后，关闭连接。
                    future.channel().writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")))
                            .addListener(ChannelFutureListener.CLOSE);
                    break;
                }
                future.channel().writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));
                TimeUnit.SECONDS.sleep(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(null != future){
                try {
                    future.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(null != client){
                client.release();
            }
        }
    }
}
