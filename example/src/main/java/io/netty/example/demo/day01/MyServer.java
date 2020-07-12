package io.netty.example.demo.day01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * <Description>
 *
 * @author wangxi
 */
public class MyServer {
    public static void main(String[] args) throws InterruptedException {
        // 配置服务端NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();  // bossGroup只处理连接事件，其他事件处理交给workerGroup.
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new MyServerInitializer());
            // 绑定端口，同步等待成功,会在这里一直等待
            // 调用sync(),阻塞知道bind()完成(一定要确保bind 8080成功).
            // 因为bind()返回一个channelFuture会立刻返回，那么继续下面代码就会有问题
            ChannelFuture f = b.bind(8080).sync();

            f.channel().closeFuture().sync();   // sync()，等待Future执行完毕。因此程序在这里卡主了

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

