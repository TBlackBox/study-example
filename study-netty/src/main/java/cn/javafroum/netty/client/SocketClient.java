/**
 * 1. 单线程组
 * 2. Bootstrap配置启动信息
 * 3. 注册业务处理Handler
 * 4. connect连接服务，并发起请求
 */
package cn.javafroum.netty.client;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 因为客户端是请求的发起者，不需要监听。
 * 只需要定义唯一的一个线程组即可。
 */
public class SocketClient {
	
	// 处理请求和处理服务端响应的线程组
	private EventLoopGroup group = null;
	// 客户端启动相关配置信息
	private Bootstrap bootstrap = null;
	
	public SocketClient(){
		init();
	}
	
	private void init(){
		group = new NioEventLoopGroup();
		bootstrap = new Bootstrap();
		// 绑定线程组
		bootstrap.group(group);
		// 设定通讯模式为NIO
		bootstrap.channel(NioSocketChannel.class);
	}
	
	public ChannelFuture doRequest(String host, int port, final ChannelHandler... handlers) throws InterruptedException{
		/*
		 * 客户端的Bootstrap没有childHandler方法。只有handler方法。
		 * 方法含义等同ServerBootstrap中的childHandler
		 * 在客户端必须绑定处理器，也就是必须调用handler方法。
		 * 服务器必须绑定处理器，必须调用childHandler方法。
		 */
		this.bootstrap.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(handlers);
			}
		});
		// 建立连接。
		ChannelFuture future = this.bootstrap.connect(host, port).sync();
		return future;
	}
	
	public void release(){
		this.group.shutdownGracefully();
	}
}
