package cn.javafroum.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;


//Sharable 注解的说明：
//作用:多个客服端可以共用这个实例，但要注意的是，不能再里面声明变量，这样不安全，声明了变量的话，
//多个客服端用这边变量会导致不安全
@ChannelHandler.Sharable
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    /**
     * 业务处理逻辑
     * 用于处理读取数据请求的逻辑。
     * msg - 读取到的数据。 默认类型是ByteBuf，是Netty自定义的。是对ByteBuffer的封装。 不需要考虑复位问题。
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException { // (2)

        //ctx - 上下文对象。其中包含于客户端建立连接的所有资源。 如： 对应的Channel
        System.out.println("通道id:" + ctx.channel().id().asLongText());


        // 获取读取的数据， 是一个缓冲。
        ByteBuf readBuffer = (ByteBuf) msg;
        // 创建一个字节数组，用于保存缓存中的数据。
        byte[] tempDatas = new byte[readBuffer.readableBytes()];
        // 将缓存中的数据读取到字节数组中。
        readBuffer.readBytes(tempDatas);
        String message = new String(tempDatas, "UTF-8");
        System.out.println("收到客服端的消息 : " + message);
        if("exit".equals(message)){
            ctx.close();
            return;
        }
        String line = "服务端接收到了客服端的消息 : " + message + "  这是返回确认";
        // 写操作自动释放缓存，避免内存溢出问题。
        ctx.writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));
        // 注意，如果调用的是write方法。不会刷新缓存，缓存中的数据不会发送到客户端，必须再次调用flush方法才行。
        // ctx.write(Unpooled.copiedBuffer(line.getBytes("UTF-8")));
        // ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        System.out.println("server exceptionCaught method run...");
        cause.printStackTrace();
        ctx.close();
    }
}